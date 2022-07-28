package com.alb.data;


import com.alb.utils.NumberFormatter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Portfolio {
    private String portfolio_id;
    private Set<Integer> positionIds;
    private final Map<Integer,Position> positionMap = new HashMap<>();

    public Portfolio(String portfolio_id,Set<Integer> positionIds) {
        this.portfolio_id = portfolio_id;
        this.positionIds = positionIds;
    }

    public double getTotalPortfolio()
    {
        double total =0.0;
        for (Position position: getPositionMap().values())
        {
            total = total+position.getValue();
        }
        return total;
    }


    public  void  print_portfolio() {

            StringBuffer buffer = new StringBuffer();
            System.out.println("##Portfolio");
            System.out.printf("%-10s%-30s%15s%15s%30s", "position", "symbol", "price", "quantity", "value");
            System.out.println();
            for (Position position : getPositionMap().values()) {

                if (position.isReportable()) {
                    System.out.printf("%-10s%-30s%15s%15s%30s",
                            position.getPosition_id(),
                            position.getProduct().getProduct_name(),
                            NumberFormatter.getTwoDecimaFormat(position.getProduct().getPrice()),
                            position.getQuantity(),
                            NumberFormatter.getTwoDecimaFormat(position.getValue()));
                    System.out.println();
                }
            }
            System.out.printf("%-30s%-10s%15s%15s%30s", "#Total Portfolio", "", "", "", NumberFormatter.getTwoDecimaFormat(getTotalPortfolio()));
            System.out.println();
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        }


    public String getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(String portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public Set<Integer> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(Set<Integer> positionIds) {
        this.positionIds = positionIds;
    }

    public Map<Integer, Position> getPositionMap() {
        return positionMap;
    }


}
