package com.alb.calculators;

import com.alb.data.MarketData;
import com.alb.products.ProductType;

import java.util.List;

public interface Calculator<T> {
    public void calculate(T t, MarketData marketData);
    public List<ProductType> applicable();
}
