package com.alb.utils;

import java.text.DecimalFormat;

public  class NumberFormatter {

    public static String getTwoDecimaFormat(Double  value)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value);
    }

}
