package com.alb.calculators;

import com.alb.products.Product;
import com.alb.products.ProductType;

import java.util.List;

public interface ProductCalculator<E extends Product> extends Calculator<E>{

    public List<ProductType> applicable();


}
