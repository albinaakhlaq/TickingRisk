package com.alb.products;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor extends AbstractBeanField<String, Date> {
    @Override
    protected Date convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
