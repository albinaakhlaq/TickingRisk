package com.alb.filters;

import java.util.List;

public interface FiltrationService<T>{

    List<T> filter(List<T> t);
}
