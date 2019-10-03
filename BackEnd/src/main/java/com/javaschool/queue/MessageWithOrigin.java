package com.javaschool.queue;

import lombok.Data;

@Data
public class MessageWithOrigin {
    private String type;
    private String origin;
    private String destination;
}
