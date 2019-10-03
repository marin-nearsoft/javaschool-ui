package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueueSenderService {
    String sendRequest(String packageType) throws JsonProcessingException;

    String sendRequest(String packageType, String origin, String destination) throws JsonProcessingException;
}
