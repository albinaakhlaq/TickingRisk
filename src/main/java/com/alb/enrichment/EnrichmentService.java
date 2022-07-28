package com.alb.enrichment;
import java.util.List;

public interface EnrichmentService<T>{
    List<T> enrich(List<T> t);
}
