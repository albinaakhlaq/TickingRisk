package com.alb.data;

import com.opencsv.bean.CsvBindByName;


public class MarketData extends SimpleData {

    @CsvBindByName
    private String ticker;
    @CsvBindByName
    private double price;

    public MarketData(String ticker, double price) {
        this.ticker = ticker;
        this.price = price;
    }
    public MarketData() {
       this("",0.0);
    }

    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MarketData{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }
}
