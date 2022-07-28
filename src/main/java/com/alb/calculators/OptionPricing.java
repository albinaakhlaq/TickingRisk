package com.alb.calculators;

import com.alb.products.EuropeanOption;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class OptionPricing implements PricingType{
    private final double price;          // strike price
    private final double strike;          // strike price
    private final double volatility;      // volatility
    private final double interest;       // interest rate
    //change to maturity date
    private final double timehorizon;     // half a year
    private final double d1;
    private final double d2;
    private final NormalDistribution distribution = new NormalDistribution(0, 1);

    public OptionPricing(EuropeanOption option) {


        price           =option.getUnderlier().getPrice();
        strike         = option.getStrike();
        volatility     = option.getVolatility();
        interest       = 2;



        timehorizon    = getTimeHorizon(option.getMaturity_date());

        d1             = (  Math.log(price / strike)
                + (interest + (Math.pow(volatility, 2) / 2)) * timehorizon)
                / (volatility * Math.sqrt(timehorizon));

        d2             = d1 - (volatility * Math.sqrt(timehorizon));
    }



    public double getCall() {
        return      (price * distribution.cumulativeProbability(d1))
                -   (strike * Math.exp(-interest * timehorizon)
                *    distribution.cumulativeProbability(d2));
    }
    public double getPut() {
        return  strike * Math.exp(-interest * timehorizon)
                * distribution.cumulativeProbability(-d2)
                - price * distribution.cumulativeProbability(-d1);
    }

    private long getTimeHorizon(Date maturityDate)
    {

        LocalDate matDate = maturityDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long noOfDays = ChronoUnit.DAYS.between(LocalDate.now(),matDate);
        return noOfDays/365;
    }


}
