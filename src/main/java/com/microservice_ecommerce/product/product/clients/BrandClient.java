package com.microservice_ecommerce.product.product.clients;

import com.microservice_ecommerce.product.product.external.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BRAND")
public interface BrandClient {

    @GetMapping("/api/brands/{id}")
    Brand getBrand(@PathVariable("id") Long id);

}
