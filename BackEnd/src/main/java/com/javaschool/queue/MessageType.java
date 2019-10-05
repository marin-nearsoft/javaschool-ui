package com.javaschool.queue;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MessageType {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String origin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destination;
}
