package com.alb.products;

import java.util.Date;

public class EuropeanOption extends   Option {

    public EuropeanOption(int id, String product_name,CommonStock underlier, OptionType type, double price, double strike, double volatility, Date maturity_date) {
        super(id,product_name,underlier, type, price, strike, volatility, maturity_date);
    }



}

