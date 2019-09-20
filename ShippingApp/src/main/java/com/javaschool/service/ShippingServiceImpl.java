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
        return queueSender.getSize();
    }

    @Override
    public List<String> getType() {
        return queueSender.getType();
    }

    @Override
    public List<String> getTime() {
        return queueSender.getTime();
    }

    @Override
    public List<String> getTransport() {
        return queueSender.getTransport();
    }

    @Override
    public List<String> getCity(){
        return queueSender.getCity();
    }
}
