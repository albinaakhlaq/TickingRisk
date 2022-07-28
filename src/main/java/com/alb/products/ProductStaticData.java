package com.alb.products;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.util.Date;

public class ProductStaticData {
    @CsvBindByName
    private int option_id;
    @CsvCustomBindByName(column = "optionType" ,converter = OptionTypeConverter.class)
    private OptionType optionType;
    @CsvBindByName
    private int underlierId;

    @CsvCustomBindByName(column = "maturityDate" ,converter = DateConvertor.class)
    private Date maturityDate;
    @CsvBindByName
    private double strikePrice;

    @CsvBindByName
    private double volatility;

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public int getUnderlierId() {
        return underlierId;
    }

    public void setUnderlierId(int underlierId) {
        this.underlierId = underlierId;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }
    @Override
    public String toString() {
        return "ProductStaticData{" +
                "option_id=" + option_id +
                ", optionType=" + optionType +
                ", underlierId=" + underlierId +
                ", maturityDate=" + maturityDate +
                ", strikePrice=" + strikePrice +
                '}';
    }
}
