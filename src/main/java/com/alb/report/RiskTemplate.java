package com.alb.report;

import java.util.List;

public abstract class RiskTemplate<T>  {

    public abstract List<T> sourceData();
    public abstract List<T> enrich();
    public abstract List<T> filter();
    public abstract void publish();

    public void run()
    {
        sourceData();
        enrich();
        filter();
        publish();
    }

}
