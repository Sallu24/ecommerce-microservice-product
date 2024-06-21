package com.microservice_ecommerce.product.product.clients;

import com.microservice_ecommerce.product.product.external.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATEGORY")
public interface CategoryClient {

    @GetMapping("/api/categories/{id}")
    Category getCategory(@PathVariable("id") Long id);

}
