package com.alb.products;

import java.util.Date;

public abstract class Option extends Product {

    private CommonStock underlier;
    private OptionType type;
    private double strike;          // strike price
    private double volatility;      // volatility
    private double interest;        // interest rate
    private Date maturity_date;     // half a year


    public Option(int id, String product_name,CommonStock underlier, OptionType type,double price, double strike, double volatility,Date maturity_date) {
        super(id,product_name,ProductType.OPTION,price);
        this.type =type;
        this.underlier = underlier;
        this.strike = strike;
        this.volatility = volatility;
        this.maturity_date = maturity_date;

    }

    public CommonStock getUnderlier() {
        return underlier;
    }

    public void setUnderlier(CommonStock underlier) {
        this.underlier = underlier;
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(double strike) {
        this.strike = strike;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Date getMaturity_date() {
        return maturity_date;
    }

    public void setMaturity_date(Date maturity_date) {
        this.maturity_date = maturity_date;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Option{" +
                "product_id=" + getProduct_id() +
                ", product_name='" + getProduct_name() + '\'' +
                ", productType=" + getProductType() +
                ", price=" + getPrice() +
                ", price=" + getStrike() +
                '}';
    }
}