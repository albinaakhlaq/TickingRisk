package com.alb.reader;

import com.alb.data.Position;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class PositionReader implements DataSource<Position> {


    @Override
    public List<Position> getData() {
        List<Position> positions = Collections.EMPTY_LIST;
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("position.csv").toURI());
            CsvtoBeanReader reader = new CsvtoBeanReader();
            positions = reader.readCsvToBeanList(path, Position.class, positions);
            return positions;
        } catch (Exception e) {
            e.printStackTrace();
            return positions;
        }




    }
}
