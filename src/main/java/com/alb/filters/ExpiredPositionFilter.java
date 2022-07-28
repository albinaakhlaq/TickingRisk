package com.alb.filters;


import com.alb.data.Position;
import com.alb.products.Option;
import com.alb.products.ProductType;

import java.util.Calendar;

public class ExpiredPositionFilter implements DataFilter<Position> {

    @Override
    public void filter(Position position) {
        if (ProductType.OPTION.equals(position.getProduct().getProductType()))
        {
            Option option = (Option) position.getProduct();
            if( option.getMaturity_date().before(Calendar.getInstance().getTime()))
            {
                position.setReportable(false);
            }

        }

    }
}
