package com.alb.orchestrator;

import static org.junit.jupiter.api.Assertions.*;

import com.alb.data.Portfolio;
import com.alb.service.PortfolioReader;
import com.alb.service.ServiceWrapper;
import net.openhft.chronicle.core.io.IOTools;

import java.util.Arrays;
import java.util.HashSet;

class OrchestratorTest {

    @org.junit.jupiter.api.Test
    void startPublisher() throws InterruptedException {
        IOTools.deleteDirWithFiles(Orchestrator.MARKET_UPDATE_QUEUE, 2);
        IOTools.deleteDirWithFiles(Orchestrator.PRODUCT_UPDATE_QUEUE, 2);
        IOTools.deleteDirWithFiles(Orchestrator.POSITION_UPDATE_QUEUE, 2);
        IOTools.deleteDirWithFiles(Orchestrator.QUEUE_OUT, 2);
        Portfolio portfolio = new Portfolio("123",new HashSet<>(Arrays.asList(1001,2001,3001)));
        ServiceWrapper<PortfolioReader> service4 = new ServiceWrapper<>(Orchestrator.POSITION_UPDATE_QUEUE, Orchestrator.QUEUE_OUT, new PortfolioReader(portfolio));
        new Orchestrator().startPublisher();
        Thread.sleep(100000);
        new Orchestrator().startPublisher();
    }
}