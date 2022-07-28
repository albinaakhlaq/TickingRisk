package com.alb.calculators;

import com.alb.data.MarketData;
import com.alb.products.CommonStock;
import com.alb.products.ProductType;

import java.util.Arrays;
import java.util.List;

public class CommonStockPriceCalculator implements ProductCalculator<CommonStock> {
    @Override
    public void calculate(CommonStock commonStock, MarketData marketData) {
        commonStock.setPrice(marketData.getPrice());
    }

    @Override
    public List<ProductType> applicable() {
        return Arrays.asList(ProductType.COMMON_STOCK);
    }
}
