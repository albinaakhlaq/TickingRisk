package com.alb.service;

import net.openhft.affinity.AffinityLock;
import net.openhft.chronicle.bytes.MethodReader;
import net.openhft.chronicle.core.io.Closeable;
import net.openhft.chronicle.core.util.ObjectUtils;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.threads.LongPauser;
import net.openhft.chronicle.threads.Pauser;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class ServiceWrapper<I extends ServiceHandler> implements Runnable, Closeable {
    private final ChronicleQueue inputQueue, outputQueue;
    private final MethodReader serviceIn;
    private final Object serviceOut;
    private final Thread thread;
    private final Pauser pauser = new LongPauser(1, 100, 500, 10_000, TimeUnit.MICROSECONDS);

    private volatile boolean closed = false;

    public ServiceWrapper(String inputPath, String outputPath, I serviceImpl) {
        Class outClass = ObjectUtils.getTypeFor(serviceImpl.getClass(), ServiceHandler.class);

        outputQueue = SingleChronicleQueueBuilder.binary(outputPath).build();
        serviceOut = outputQueue.acquireAppender().methodWriter(outClass);
        serviceImpl.init(serviceOut);

        inputQueue = SingleChronicleQueueBuilder.binary(inputPath).build();
        serviceIn = inputQueue.createTailer().methodReader(serviceImpl);

        thread = new Thread(this, new File(inputPath).getName() + " to " + new File(outputPath).getName());
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        AffinityLock lock = AffinityLock.acquireLock();
        try {
            while (!closed) {
                if (serviceIn.readOne()) {
                    queueDump();
                    pauser.reset();
                } else {
                    pauser.pause();
                }
            }
        } finally {
            lock.release();
        }
    }

    public void queueDump()
    {
       // System.out.println(outputQueue.dump());
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
