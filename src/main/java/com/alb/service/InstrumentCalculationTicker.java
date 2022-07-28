package com.alb.service;


import com.alb.calculators.CalculationService;
import com.alb.calculators.InstrumentCalculationService;
import com.alb.data.MarketData;
import com.alb.data.SimpleData;
import com.alb.products.Option;
import com.alb.products.Product;
import com.alb.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InstrumentCalculationTicker implements UpdateHandler<SimpleData>, ServiceHandler<UpdateHandler> {
    private UpdateHandler outservice;
    private List<Product> products;
    private CalculationService service;
    private Map<String, List<Product>> productMap;


    public InstrumentCalculationTicker(CalculationService service, List<Product> products) {
        this.service = service;
        this.products = products;
        this.productMap = getProductMap();

    }

    @Override
    public void init(UpdateHandler outservice) {
        this.outservice = outservice;
    }

    @Override
    public void on_update(SimpleData data) {
        MarketData mkt= (MarketData) data;
        List<Product> products = productMap.get(mkt.getTicker());
        ((InstrumentCalculationService)getService()).setMarket(mkt);
        for (Product product:products) {
            getService().executes(product);

           outservice.on_update(product);
            //System.out.printf("%-10s",product.getProduct_name() +" change to \n"+ NumberFormatter.getTwoDecimaFormat(product.getPrice()));
        }


    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public CalculationService getService() {
        return service;
    }

    public void setService(CalculationService service) {
        this.service = service;
    }

    private Map<String, List<Product>> getProductMap()
    {

        Map<String, List<Product>> postsPerType = new ConcurrentHashMap<>();
        for(Product product:getProducts())
        {
            String key = product.getProduct_name();
            if(ProductType.OPTION.equals(product.getProductType()))
            {
                key =((Option)product).getUnderlier().getProduct_name();
            }

            if (postsPerType.containsKey(key))
            {
                    List<Product> templ =postsPerType.get(key);
                    templ.add(product);
            }
            else
            {
                postsPerType.put(key,new ArrayList<Product>(Arrays.asList(product)));

            }

        }

      return postsPerType;
    }





}
