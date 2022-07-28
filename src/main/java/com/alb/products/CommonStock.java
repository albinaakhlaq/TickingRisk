package com.alb.products;

public class CommonStock extends Product {

    public CommonStock(int product_id, String product_name,double price) {
        super(product_id, product_name,ProductType.COMMON_STOCK,price);
    }


    @Override
    public String toString() {
        return "CommonStock{" +
                "product_id=" + getProduct_id() +
                ", product_name='" + getProduct_name()+ '\'' +
                ", productType=" + getProductType() +
                ", price=" + getPrice() +
                '}';
    }
}


