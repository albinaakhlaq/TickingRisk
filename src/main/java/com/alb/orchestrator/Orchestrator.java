package com.alb.orchestrator;

import com.alb.calculators.CommonStockPriceCalculator;
import com.alb.calculators.InstrumentCalculationService;
import com.alb.calculators.OptionMarketPriceCalculator;
import com.alb.calculators.ProductCalculator;
import com.alb.data.Portfolio;
import com.alb.data.Position;
import com.alb.producers.MockMarketProducer;
import com.alb.products.Product;
import com.alb.reader.PositionReader;
import com.alb.service.InstrumentCalculationTicker;
import com.alb.service.PortfolioReader;
import com.alb.service.PositionUpdateHandler;
import com.alb.service.ServiceWrapper;
import com.alb.sourcing.PositionSourcer;
import com.alb.report.RiskTemplate;
import com.alb.report.TickingRisk;
import net.openhft.chronicle.core.io.IOTools;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Orchestrator {
    public static String  dir = "Queue/";
    public static String MARKET_UPDATE_QUEUE = dir+"market_update";
    public static String PRODUCT_UPDATE_QUEUE = dir+"product_update";
    public static String POSITION_UPDATE_QUEUE = dir+"position_update";
    public static String QUEUE_OUT = dir+"final";
    public void startPublisher() {
        MockMarketProducer mock =new MockMarketProducer();
        RiskTemplate<Position> template = new TickingRisk(new PositionSourcer(new PositionReader()));
        template.run();
        List<Position> positions = ((TickingRisk)template).getPositions();

        Map<Integer, Position> portfolio_pos = positions.stream()
                .collect(Collectors.toConcurrentMap(Position::getPosition_id, Function.identity()));
        List<Product> products = new ArrayList<>();
        for (Position position:positions)
        {
            products.add(position.getProduct());
        }

        List<ProductCalculator> calcs = Arrays.asList(new OptionMarketPriceCalculator(),new CommonStockPriceCalculator());
        ServiceWrapper<InstrumentCalculationTicker> service2 = new ServiceWrapper<>(MARKET_UPDATE_QUEUE ,PRODUCT_UPDATE_QUEUE, new InstrumentCalculationTicker(new InstrumentCalculationService(calcs),products));
        ServiceWrapper<PositionUpdateHandler> service3 = new ServiceWrapper<>(PRODUCT_UPDATE_QUEUE, POSITION_UPDATE_QUEUE, new PositionUpdateHandler(positions));

    }


}
