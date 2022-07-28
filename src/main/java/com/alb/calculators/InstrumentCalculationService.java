package com.alb.calculators;

import com.alb.data.MarketData;
import com.alb.data.Position;
import com.alb.products.Product;

import java.util.List;

public class InstrumentCalculationService implements CalculationService<Product> {

    private List<ProductCalculator> calculators;
    private List<Position> positions;

    private MarketData market;

    public InstrumentCalculationService(List<ProductCalculator> calculators) {
        this.calculators = calculators;
    }


    @Override
    public void executes(Product product) {
        for(Calculator calculator:getCalculators())
        {
            if (calculator.applicable().contains(product.getProductType())) {
                calculator.calculate(product, getMarket());
            }
        }

    }


    public List<ProductCalculator> getCalculators() {
        return calculators;
    }

    public void setCalculators(List<ProductCalculator> calculators) {
        this.calculators = calculators;
    }

    public MarketData getMarket() {
        return market;
    }

    public void setMarket(MarketData market) {
        this.market = market;
    }

}
