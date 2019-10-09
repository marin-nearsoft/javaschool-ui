package com.shipping.backend.models.request;

import lombok.Data;

@Data
public class RouteRequest {
    private String origin;
    private String destination;
}
