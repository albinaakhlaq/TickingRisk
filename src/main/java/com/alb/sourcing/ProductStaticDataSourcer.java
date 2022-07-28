package com.alb.sourcing;

import com.alb.products.ProductStaticData;
import com.alb.reader.DataSource;

import java.util.Collections;
import java.util.List;

public class ProductStaticDataSourcer implements DataSourcer<ProductStaticData> {
    private DataSource source;
    public ProductStaticDataSourcer(DataSource source) {
        this.source= source;
    }

    @Override
    public List<ProductStaticData> source() {
        try {
            return source.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }


}
