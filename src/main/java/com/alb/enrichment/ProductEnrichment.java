package com.alb.enrichment;

import com.alb.data.Position;
import com.alb.products.Product;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductEnrichment implements EnrichmentService<Position> {

    private List<Product> products;

    public ProductEnrichment(List products) {
        this.products = products;
    }



    @Override
    public List<Position> enrich(List<Position> positions) {
        Map<Integer, Product> productsMap = products.stream()
                .collect(Collectors.toMap(Product::getProduct_id, Function.identity()));
        for(Position position : positions)
        {
           Product product = productsMap.get(position.getProductId());
           if (product != null)
           {
               position.setProduct(product);
           }
           else
           {
               System.out.println(position.getPosition_id()+" Positions can not be enriched ");
           }

        }


        return positions;

    }
}
