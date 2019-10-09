package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QueueMessageRequest implements Serializable {

    private String type;
    private String origin;
    private String destination;
}
