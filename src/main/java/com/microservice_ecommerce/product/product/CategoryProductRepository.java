package com.microservice_ecommerce.product.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM CategoryProduct cp WHERE cp.product.id = :productId")
    void deleteByProductId(Long productId);

}
