package com.alb.reader;

import com.alb.products.ProductStaticData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ProductStaticDataReader implements DataSource<ProductStaticData> {
    @Override
    public List<ProductStaticData> getData() throws Exception {
        List<ProductStaticData> productStaticData= Collections.EMPTY_LIST;

        Path path = Paths.get(ClassLoader.getSystemResource("product_detail.csv").toURI());
        CsvtoBeanReader reader = new CsvtoBeanReader();
        productStaticData = reader.readCsvToBeanList(path, ProductStaticData.class, productStaticData);
        return productStaticData;

    }
}
