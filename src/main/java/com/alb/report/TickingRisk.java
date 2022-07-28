package com.alb.report;

import com.alb.data.Position;
import com.alb.enrichment.ProductEnrichment;
import com.alb.filters.ExcludedProductFilter;
import com.alb.filters.ExpiredPositionFilter;
import com.alb.filters.PositionFiltrationService;
import com.alb.products.Product;
import com.alb.reader.ProductReader;
import com.alb.report.RiskTemplate;
import com.alb.sourcing.DataSourcer;
import com.alb.sourcing.ProductSourcer;

import java.util.Arrays;
import java.util.List;


public class TickingRisk extends RiskTemplate<Position> {

    DataSourcer source;
    List<Position> positions;
    public TickingRisk(DataSourcer source) {
        this.source = source;
    }

    @Override
    public  List<Position> sourceData() {
            try {
               setPositions(getSource().source());
            }catch (Exception e) {
                e.printStackTrace();
            }

        return getPositions();
    }

    @Override
    public List<Position> enrich() {
        ProductSourcer productSource = new ProductSourcer(new ProductReader());
        List<Product> products  = productSource.source();
        ProductEnrichment enrichment = new ProductEnrichment(products);
        enrichment.enrich(getPositions());
        return getPositions();
    }

    @Override
    public List<Position> filter() {
        PositionFiltrationService service = new PositionFiltrationService(Arrays.asList(new ExcludedProductFilter(),new ExpiredPositionFilter()));
        service.filter(getPositions());
        return getPositions();
    }

    @Override
    public void publish() {

    }


    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public DataSourcer getSource() {
        return source;
    }

    public void setSource(DataSourcer sources) {
        this.source = sources;
    }
}
