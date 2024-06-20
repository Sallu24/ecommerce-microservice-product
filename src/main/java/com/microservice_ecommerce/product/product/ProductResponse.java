package com.microservice_ecommerce.product.product;

public class ProductResponse {

    protected Long id;

    protected String name;

    private Double price;

    private String sku;

    private Integer qty;

    private Boolean inStock;

    public ProductResponse(
            Long id,
            String name,
            Double price,
            String sku,
            Integer qty,
            Boolean inStock
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.qty = qty;
        this.inStock = inStock;
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

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", qty=" + qty +
                ", inStock=" + inStock +
                '}';
    }

}
