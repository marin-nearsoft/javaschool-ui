package com.javaschool.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueueSenderService {
    String SendRequest(String packageType) throws JsonProcessingException;
}
