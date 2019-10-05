package com.nearsoft.project.resources.rabbit;

public enum SourceMsg {
    TYPES("{\"type\":\"packageType\"}"),
    SIZES("{\"type\":\"packageSize\"}");

    public final String source;
    private SourceMsg(String source) {
        this.source = source;
    }
}
