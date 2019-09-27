package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueueSenderService {
    String sendRequest(String packageType) throws JsonProcessingException;
}
