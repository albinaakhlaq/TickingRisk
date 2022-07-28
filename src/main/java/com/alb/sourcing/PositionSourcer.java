package com.alb.sourcing;

import com.alb.reader.DataSource;

import java.util.Collections;
import java.util.List;

public class PositionSourcer<Position> implements DataSourcer<com.alb.data.Position> {
    private DataSource source;

    public PositionSourcer(DataSource source) {
        this.source = source;
    }

    @Override
    public List<com.alb.data.Position> source() {
        try {
            return source.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
