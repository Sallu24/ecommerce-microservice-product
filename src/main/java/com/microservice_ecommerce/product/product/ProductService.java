package com.microservice_ecommerce.product.product;

import com.microservice_ecommerce.product.product.clients.BrandClient;
import com.microservice_ecommerce.product.product.clients.CategoryClient;
import com.microservice_ecommerce.product.product.external.Brand;
import com.microservice_ecommerce.product.product.external.Category;
import com.microservice_ecommerce.product.product.mapper.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    protected ProductRepository productRepository;
    protected CategoryProductRepository categoryProductRepository;
    protected BrandClient brandClient;
    protected CategoryClient categoryClient;

    public ProductService(
            ProductRepository productRepository,
            CategoryProductRepository categoryProductRepository,
            BrandClient brandClient,
            CategoryClient categoryClient
    ) {
        this.productRepository = productRepository;
        this.categoryProductRepository = categoryProductRepository;
        this.brandClient = brandClient;
        this.categoryClient = categoryClient;
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
    }

    protected ProductResponse view(Long id) {
        Product product = findById(id);

        return convertToDTO(product);
    }

    protected void update(Long id, ProductCreationDTO productCreationDTO) {
        Product existingProduct = findById(id);

        saveOrUpdateProduct(existingProduct, productCreationDTO);
    }

    protected void delete(Long id) {
        Product existingProduct = findById(id);

        productRepository.deleteById(existingProduct.getId());
    }

    private void saveOrUpdateProduct(Product product, ProductCreationDTO productCreationDTO) {
        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPrice());
        product.setSku(productCreationDTO.getSku());
        product.setQty(productCreationDTO.getQty());
        product.setInStock(productCreationDTO.getIn_stock());

        if (productCreationDTO.getBrand_id() != null) {
            Brand brand = brandClient.getBrand(productCreationDTO.getBrand_id());

            if (brand != null) {
                product.setBrandId(brand.getId());
            }
        }

        productRepository.save(product);

        syncCategories(product, productCreationDTO.getCategory_ids());
    }

    private void syncCategories(Product product, List<Long> categoryIds) {
        if (categoryIds != null) {
            categoryProductRepository.deleteByProductId(product.getId());

            for (Long categoryId : categoryIds) {
                Category category = categoryClient.getCategory(categoryId);

                if (category != null) {
                    CategoryProduct categoryProduct = new CategoryProduct();
                    categoryProduct.setCategoryId(category.getId());
                    categoryProduct.setProduct(product);

                    categoryProductRepository.save(categoryProduct);
                }
            }
        }
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product found against this id: " + id));
    }

    private ProductResponse convertToDTO(Product product) {
        List<Category> categories = new ArrayList<>();

        List<CategoryProduct> categoryProducts = product.getCategoryProducts();

        if (categoryProducts != null && !categoryProducts.isEmpty()) {
            categories = categoryProducts.stream()
                    .map(categoryProduct -> {
                        Category category = categoryClient.getCategory(categoryProduct.getCategoryId());

                        if (category != null) {
                            return new Category(category.getId(), category.getName());
                        }

                        return null;
                    })
                    .toList();
        }

        if (product.getBrandId() != null) {
            Brand brand = brandClient.getBrand(product.getBrandId());

            return ProductMapper.productResponse(product, brand, categories);
        }

        return ProductMapper.productResponse(product, null, categories);
    }

}
