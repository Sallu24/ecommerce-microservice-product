package com.microservice_ecommerce.product.product;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String sku;

    private Integer qty;

    @Column(name = "in_stock")
    private Boolean inStock;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    @JoinTable(
//            name = "category_product",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private Long categoryId;

    public Product(
            Long id,
            String name,
            Double price,
            String sku,
            Integer qty,
            Boolean inStock,
            Date createdAt,
            Date updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.qty = qty;
        this.inStock = inStock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public List<Category> getCategories() {
//        return categories;
//    }

//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", qty=" + qty +
                ", inStock=" + inStock +
                ", brandId=" + brandId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
