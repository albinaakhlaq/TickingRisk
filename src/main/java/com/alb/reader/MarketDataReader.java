package com.alb.reader;

import com.alb.data.MarketData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class MarketDataReader implements DataSource<MarketData> {

    @Override
    public List<MarketData> getData() throws Exception {
        List<MarketData> marketDataList= Collections.EMPTY_LIST;

            Path path = Paths.get(ClassLoader.getSystemResource("market_data.csv").toURI());
            CsvtoBeanReader reader = new CsvtoBeanReader();
            marketDataList = reader.readCsvToBeanList(path, MarketData.class, marketDataList);

        return marketDataList;

    }
}
