package com.microservice_ecommerce.product.product;

import com.microservice_ecommerce.product.product.external.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    protected ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResponses);
    }

    protected void save(ProductCreationDTO productCreationDTO) {
        Product product = new Product();

        saveOrUpdateProduct(product, productCreationDTO);

        productRepository.save(product);
    }

    protected ProductResponse view(Long id) {
        Product product = findById(id);

        return convertToDTO(product);
    }

    protected void update(Long id, ProductCreationDTO productCreationDTO) {
        Product existingProduct = findById(id);

        saveOrUpdateProduct(existingProduct, productCreationDTO);

        productRepository.save(existingProduct);
    }

    protected void delete(Long id) {
        Product existingProduct = findById(id);

        productRepository.deleteById(existingProduct.getId());
    }

    private void saveOrUpdateProduct(Product product, ProductCreationDTO productCreationDTO) {
        RestTemplate restTemplate = new RestTemplate();

        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPrice());
        product.setSku(productCreationDTO.getSku());
        product.setQty(productCreationDTO.getQty());
        product.setInStock(productCreationDTO.getIn_stock());

        if (productCreationDTO.getBrand_id() != null) {
            Brand brand = restTemplate.getForObject(
                    "http://localhost:8091/api/brands/" + productCreationDTO.getBrand_id(),
                    Brand.class
            );

            if (brand != null) {
                product.setBrandId(brand.getId());
            }
        }

        if (productCreationDTO.getCategory_ids() != null) {
//            List<Category> categories = productCreationDTO.getCategory_ids().stream()
//                    .map(
//                            categoryId -> categoryRepository.findById(categoryId)
//                                    .orElseThrow(
//                                            () -> new CategoryNotFoundException("No category found against this id: " + categoryId)
//                                    )
//                    )
//                    .collect(Collectors.toList());
//            product.setCategories(categories);
        }
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product found against this id: " + id));
    }

    private ProductResponse convertToDTO(Product product) {
//        BrandResponse brandResponse = null;
//        Brand brand = product.getBrand();

//        if (brand != null) {
//            brandResponse = new BrandResponse(
//                    brand.getId(),
//                    brand.getName()
//            );
//        }

//        List<CategoryResponse> categoryResponses = null;
//        List<Category> categories = product.getCategories();

//        if (categories != null && !categories.isEmpty()) {
//            categoryResponses = categories.stream()
//                    .map(category -> new CategoryResponse(category.getId(), category.getName()))
//                    .toList();
//        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSku(),
                product.getQty(),
                product.getInStock()
//                brandResponse,
//                categoryResponses
        );
    }

}
