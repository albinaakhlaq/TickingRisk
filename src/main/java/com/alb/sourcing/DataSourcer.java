package com.alb.sourcing;

import java.util.List;

public interface DataSourcer<T> {
    List<T> source();
}
