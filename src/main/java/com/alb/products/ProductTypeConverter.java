package com.alb.products;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ProductTypeConverter extends AbstractBeanField<String,ProductType>{
    @Override
    protected ProductType convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return ProductType.valueOf(value);
    }
}
