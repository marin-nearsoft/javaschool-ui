package com.nearsoft.project.resources.implementation;

import java.io.IOException;
import java.util.List;

public interface ShippingLists {

    public List<String> getSizes(String source) throws IOException;
    public List<String> getTypes(String source) throws IOException;
}
