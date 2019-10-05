package com.nearsoft.project.resources.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.project.resources.source.Sizes;
import com.nearsoft.project.resources.source.Types;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShippingListsImpl implements ShippingLists {


    private ObjectMapper mapper;

    public ShippingListsImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<String> getSizes(String jsonSource) throws IOException {
        List<String> list = new ArrayList<>();
        try {
                List<Sizes> listSizes = Arrays.asList(mapper.readValue(jsonSource, Sizes[].class));
                for (Sizes size : listSizes) {
                    list.add(size.getDescription());
            }
        } catch (Exception e){
            return list;
        }
        return list;
    }

    @Override
    public List<String> getTypes(String jsonSource) throws IOException
    {
        List<String> list = new ArrayList<>();
        try{
            List<Types> listTypes = Arrays.asList(mapper.readValue(jsonSource, Types[].class));
            for (Types type : listTypes) {
                list.add(type.getDescription());
        }
        }catch(Exception e){
            System.out.println(e.toString());
            return list;
        }
        return list;
    }
}
