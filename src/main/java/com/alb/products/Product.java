package com.alb.products;

import com.alb.data.SimpleData;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public  class Product extends SimpleData {
   @CsvBindByName
   private int product_id;
   @CsvBindByName
   private String product_name;
   @CsvCustomBindByName(column = "productType" ,converter = ProductTypeConverter.class)
   private ProductType productType;
    @CsvBindByName
    private double price;
   public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Product(){}
   public Product(int product_id, String product_name,ProductType productType,double price) {
      this.product_id = product_id;
      this.product_name = product_name;
      this.productType = productType;
      this.price = price;
   }

   public int getProduct_id() {
      return product_id;
   }

   public void setProduct_id(int product_id) {
      this.product_id = product_id;
   }

   public String getProduct_name() {
      return product_name;
   }

   public void setProduct_name(String product_name) {
      this.product_name = product_name;
   }


    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }


    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", productType=" + productType +
                ", price=" + price +
                '}';
    }
}
