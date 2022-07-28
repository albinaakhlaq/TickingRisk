package com.alb.filters;

import com.alb.data.Position;

import java.util.List;

public class PositionFiltrationService implements FiltrationService<Position> {
    List<DataFilter> filters;

    public PositionFiltrationService(List<DataFilter> filters) {
        this.filters = filters;
    }


    @Override
    public List<Position> filter(List<Position> positions) {
       for(Position position: positions)
       {
           for (DataFilter filter: filters)
           {
               filter.filter(position);
           }
       }
       return positions;
    }
}
