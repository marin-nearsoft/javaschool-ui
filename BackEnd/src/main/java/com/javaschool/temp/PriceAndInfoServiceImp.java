package com.javaschool.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.configuration.CustomException;
import com.javaschool.modelmapper.Information;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAndInfoServiceImp implements PriceAndInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceAndInfoServiceImp.class);
    private ObjectMapper mapper;

    public PriceAndInfoServiceImp(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Information> getInformation() {
        List<Information> information;
        LOGGER.info("Generating the Shipping Information List.");
        try {

            String response = "[{\"folio\":\"00598\",\"path\":\"Chihuahua, Tampico, Puebla, Tuxtla Gutierrez, Durango, Aguascalientes\",\"price\":\"29.18\"},{\"folio\":\"00666\",\"path\":\"Sonora, Sinaloa\",\"price\":\"99566.6\"}]";
            information = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Information.class));

            LOGGER.info("Shipping Information List generated successfully.");
            return information;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Shipping Information List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
    }

    @Override
    public Information postInformation(double price, String path) {
        int random = (int) (Math.random() * ((99999 - 1) + 1)) + 1;
        Information sendinfo;
        LOGGER.info("Posting the Shipping Information List.");
        try {
            String response = "{\"folio\":\"" + random + "\",\"path\":\"" + path + "\",\"price\":\"" + price + "\"}";
            sendinfo = mapper.readValue(response, mapper.getTypeFactory().constructType(Information.class));
            LOGGER.info("Posting Information successfully.");
            return sendinfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't post the Shipping Information. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }

    }
}
