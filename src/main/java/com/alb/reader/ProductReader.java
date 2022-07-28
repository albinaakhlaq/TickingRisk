package com.alb.reader;

import com.alb.products.Product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ProductReader implements DataSource<Product>{

    @Override
    public List<Product> getData() throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource("product.csv").toURI());
        List<Product> products = Collections.EMPTY_LIST;
        CsvtoBeanReader reader = new CsvtoBeanReader();
        products = reader.readCsvToBeanList(path, Product.class, products);
        return products;
    }
}
