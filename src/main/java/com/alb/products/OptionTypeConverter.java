package com.alb.products;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class OptionTypeConverter extends AbstractBeanField<String,OptionType> {

    @Override
    protected OptionType convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return OptionType.valueOf(value);
    }
}
