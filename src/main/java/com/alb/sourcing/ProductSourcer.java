package com.alb.sourcing;

import com.alb.products.CommonStock;
import com.alb.products.EuropeanOption;
import com.alb.products.Product;
import com.alb.products.ProductStaticData;
import com.alb.reader.DataSource;
import com.alb.reader.ProductStaticDataReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductSourcer implements DataSourcer<Product> {
    private DataSource source;

    public ProductSourcer(DataSource source) {
        this.source = source;
    }


    @Override
    public List<Product> source() {
        List<Product> temp_product = Collections.EMPTY_LIST;
        try {
            ProductStaticDataSourcer reader = new ProductStaticDataSourcer(new ProductStaticDataReader());
            List<ProductStaticData> staticDataL = reader.source();
            Map<Integer, ProductStaticData> product_details = staticDataL.stream()
                    .collect(Collectors.toMap(ProductStaticData::getOption_id, Function.identity()));

            List<Product> products = source.getData();
            Map<Integer, Product> productsMap = products.stream()
                    .collect(Collectors.toMap(Product::getProduct_id, Function.identity()));

            Map<Integer, Product> temp = new HashMap<>();

            for (int key : productsMap.keySet()) {
                Product product = productsMap.get(key);
                switch (product.getProductType()) {
                    case COMMON_STOCK:
                        Product tempProduct = new CommonStock(product.getProduct_id(), product.getProduct_name(), product.getPrice());
                        temp.put(product.getProduct_id(), tempProduct);
                        break;

                    case OPTION:
                        ProductStaticData productDetail = product_details.get(product.getProduct_id());

                        CommonStock stock = new CommonStock(productDetail.getUnderlierId(), productsMap.get(productDetail.getUnderlierId()).getProduct_name(), product.getPrice());
                        EuropeanOption option = new EuropeanOption(product.getProduct_id(), product.getProduct_name(), stock, productDetail.getOptionType(), product.getPrice() , productDetail.getStrikePrice(), productDetail.getVolatility(), productDetail.getMaturityDate());
                        temp.put(product.getProduct_id(), option);
                        break;
                }
                temp_product=  temp.values().stream()
                        .collect(Collectors.toList());


            }

        } catch (Exception e) {
            e.printStackTrace();


        }
        return temp_product;
    }
}
