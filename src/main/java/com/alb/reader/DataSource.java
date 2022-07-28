package com.alb.reader;

import java.util.List;

public interface DataSource<T> {
    List<T> getData() throws Exception;
}
