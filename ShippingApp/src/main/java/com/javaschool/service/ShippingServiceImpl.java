package com.javaschool.service;

import com.javaschool.queue.QueueSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    private QueueSender queueSender;

    public ShippingServiceImpl(final QueueSender queueSender) {
        this.queueSender = queueSender;
    }

    @Override
    public List<String> getSize() {
        List<String> sizes = queueSender.getSize();
        return sizes;
    }

    @Override
    public List<String> getType() {
        List<String> types = queueSender.getType();
        return types;
    }

    @Override
    public List<String> getTime() {
        List<String> times = queueSender.getTime();
        return times;
    }

    @Override
    public List<String> getTransport() {
        List<String> transports = queueSender.getTransport();
        return transports;
    }
}
