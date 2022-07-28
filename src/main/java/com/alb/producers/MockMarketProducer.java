package com.alb.producers;

import com.alb.orchestrator.Orchestrator;
import com.alb.data.MarketData;
import com.alb.reader.MarketDataReader;
import com.alb.service.UpdateHandler;
import net.openhft.affinity.AffinityLock;
import net.openhft.chronicle.core.io.Closeable;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.threads.LongPauser;
import net.openhft.chronicle.threads.Pauser;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MockMarketProducer implements Runnable, Closeable {

    private UpdateHandler serviceIn;
    public static String MARKET_QUEUE_PATH = "Queue/market_data";
    private final Pauser pauser = new LongPauser(1, 100, 500, 10_000, TimeUnit.MICROSECONDS);
    private List<MarketData> dataList;
    private final Thread thread;
    private volatile boolean closed = false;

    public MockMarketProducer() {
        serviceIn = SingleChronicleQueueBuilder.binary(Orchestrator.MARKET_UPDATE_QUEUE).build().acquireAppender().methodWriter(UpdateHandler.class);
        try {
            dataList =new MarketDataReader().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        AffinityLock lock = AffinityLock.acquireLock();
        try {
            while(!closed) {
                for (MarketData data:dataList)
                {
                    data.setPrice(generate_random_price(data.getPrice()));
                    this.serviceIn.on_update(data);
                    Thread.sleep(generate_random_timer());
            }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }
    }

    public static double generate_random_price(double price){
        Random r = new Random();
        return  r.nextDouble()*1*price/100+price;

    }

    public static Long generate_random_timer(){
        Random r = new Random();
        return  (long)(r.nextDouble()*3000);

    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }
}
