package com.alb.service;

import com.alb.data.Position;
import com.alb.data.SimpleData;
import com.alb.products.Product;
import com.alb.products.ProductType;
import com.alb.utils.NumberFormatter;

import java.util.List;

public class PositionUpdateHandler implements UpdateHandler<SimpleData>, ServiceHandler<UpdateHandler>{
    private UpdateHandler updateHandler;
    private List<Position> positions;

    public PositionUpdateHandler(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public void init(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    @Override
    public void on_update(SimpleData data) {

            Product product = (Product) data;
            for (Position position : positions) {
                if (position.getProductId() == product.getProduct_id()) {
                    Position pos = position;
                    pos.setProduct(product);
                    updateHandler.on_update(pos);
                    if(ProductType.COMMON_STOCK.equals(product.getProductType())) {
                        System.out.printf("%-10s", product.getProduct_name() + " change to " + NumberFormatter.getTwoDecimaFormat(product.getPrice()) + "\n");

                    }
                }

        }

    }


}
