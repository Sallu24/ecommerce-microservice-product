package com.microservice_ecommerce.product.product.mapper;

import com.microservice_ecommerce.product.product.Product;
import com.microservice_ecommerce.product.product.ProductResponse;
import com.microservice_ecommerce.product.product.external.Brand;

public class ProductMapper {

    public static ProductResponse productResponse(Product product, Brand brand) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setSku(product.getSku());
        productResponse.setQty(product.getQty());
        productResponse.setInStock(product.getInStock());
        productResponse.setBrand(brand);

        return productResponse;
    }

}
