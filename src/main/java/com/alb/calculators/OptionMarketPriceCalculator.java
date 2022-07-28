package com.alb.calculators;

import com.alb.data.MarketData;
import com.alb.products.EuropeanOption;
import com.alb.products.OptionType;
import com.alb.products.ProductType;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class OptionMarketPriceCalculator implements ProductCalculator<EuropeanOption> {

    @Override
    public void calculate(EuropeanOption europeanOption,MarketData marketData) {
        if(europeanOption.getMaturity_date().after(Calendar.getInstance().getTime()))
        {
            europeanOption.getUnderlier().setPrice(marketData.getPrice());
            OptionPricing pricer = new OptionPricing(europeanOption);
                if (europeanOption.getType() == OptionType.CALL)
                {
                    europeanOption.setPrice(pricer.getCall());
                }
                else
                {
                    europeanOption.setPrice(pricer.getPut());
                }

            }
    }

    @Override
    public List<ProductType> applicable() {
        return Arrays.asList(ProductType.OPTION);
    }
}

