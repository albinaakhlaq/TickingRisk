package com.alb.data;


import com.alb.products.CommonStock;
import com.alb.products.Option;
import com.alb.products.Product;
import com.alb.products.ProductType;
import com.alb.utils.NumberFormatter;
import com.opencsv.bean.CsvBindByName;


public class Position extends SimpleData{

    @CsvBindByName
    private int position_id;
    @CsvBindByName
    private int quantity;
    @CsvBindByName
    private int productId;
    private Product product;
    private boolean reportable =true;

    public Position(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getValue()
    {
        return getPrice()*getQuantity();
    }



    public double getPrice()
    {
        double price = 0.0;
        if(ProductType.COMMON_STOCK.equals(product.getProductType()))
        {
           price =((CommonStock)product).getPrice();
        }
        else{
            price =((Option)product).getPrice();
        }
        return price;
    }


    public boolean isReportable() {
        return reportable;
    }

    public void setReportable(boolean reportable) {
        this.reportable = reportable;
    }


    @Override
    public String toString() {
        return "Position{" +
                " position_id='" + position_id + '\'' +
                ", product=" + product.getProduct_name() +
                ", price=" + NumberFormatter.getTwoDecimaFormat(getPrice()) +
                ", quantity=" + quantity +
                ", value=" + NumberFormatter.getTwoDecimaFormat(getValue() )+
                '}';
    }





}
