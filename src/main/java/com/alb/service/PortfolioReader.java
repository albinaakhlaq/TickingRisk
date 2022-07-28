package com.alb.service;

import com.alb.data.Portfolio;
import com.alb.data.Position;
import com.alb.data.SimpleData;
import com.alb.products.ProductType;


public class PortfolioReader implements UpdateHandler<SimpleData>, ServiceHandler<UpdateHandler>{
    private UpdateHandler updateHandler;
    private Portfolio portfolio;

    public PortfolioReader(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void init(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;

    }

    @Override
    public void on_update(SimpleData data) {
       Position position = (Position) data;
       //having issue with map if it's added directly here
       Position copyPosition = new Position();
       position.copyTo(copyPosition);
       portfolio.getPositionMap().put(position.getPosition_id(),copyPosition);

        if(ProductType.COMMON_STOCK.equals(copyPosition.getProduct().getProductType()))
        {
            portfolio.print_portfolio();
        }
    }
}
