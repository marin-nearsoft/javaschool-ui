package com.javaschool.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.configuration.CustomException;
import com.javaschool.dijkstra.RouteList;
import com.javaschool.modelmapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueResponseServiceImp implements QueueResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderServiceImp.class);
    private  QueueSenderService queueSenderService;
    private ObjectMapper mapper;


    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService = queueSenderService;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types;
        LOGGER.info("Generating the Type List.");
        try {
            String response = queueSenderService.sendRequest("packageType", null, null);
//            String response = "[{\"id\":1,\"description\":\"Box\",\"price\":10},{\"id\":2,\"description\":\"Envelope\",\"price\":6}]";
            types = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Type List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Type List generated successfully.");
        return types;
    }

    @Override
    public List<PackageSize> getSize() {
        List<PackageSize> sizes;
        LOGGER.info("Generating the Size List.");
        try {
            String response = queueSenderService.sendRequest("packageSize", null, null);
//            String response = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Big\",\"priceFactor\":12}]";
            sizes = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, PackageSize.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Size List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Size List generated successfully.");

        return sizes;
    }

    @Override
    public List<TransportType> getTransport() {
        List<TransportType> transports;
        LOGGER.info("Generating the Transport List.");
        try {
            String response = queueSenderService.sendRequest("transportType", null, null);
//            String response = "[{\"id\":1,\"description\":\"Land\",\"pricePerMile\":2},{\"id\":2,\"description\":\"Air\",\"pricePerMile\":9}]";
            transports = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, TransportType.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Transport List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Transport List generated successfully.");

        return transports;
    }

    @Override
    public List<TransportVelocity> getVelocity() {
        List<TransportVelocity> velocities;
        LOGGER.info("Generating the Velocity List.");
        try {
            String response = queueSenderService.sendRequest("transportVelocity", null, null);
//            String response = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5},{\"id\":2,\"description\":\"Express\",\"priceFactor\":15}]";
            velocities = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, TransportVelocity.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Time List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Velocity List generated successfully.");

        return velocities;
    }

    @Override
    public List<Cities> getCity() {
        List<Cities> cities;
        LOGGER.info("Generating the Cities List.");
        try {
            String response = queueSenderService.sendRequest("city", null, null);
//            String response = "[{\"id\":1,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":2,\"name\":\"Chihuahua\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":3,\"name\":\"Aguascalientes\",\"tax\":10,\"seaport\":false,\"airport\":false}]";

            cities = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Cities.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Cities List. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Cities List generated successfully.");

        return cities;
    }

    @Override
    public List<RouteList> getRoute(String origin, String destination) {
        List<RouteList> route;
        LOGGER.info("Generating the Route path.");
        try {
            String response = queueSenderService.sendRequest("routesList", origin, destination);
//            String response = "[{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Durango\",\"distance\":\"58\"},{\"from\":\"Tampico\",\"to\":\"San Luis Potosi\",\"distance\":\"96\"},{\"from\":\"Puebla\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"9\"},{\"from\":\"Ensenada\",\"to\":\"Cancun\",\"distance\":\"55\"},{\"from\":\"La Paz\",\"to\":\"Ensenada\",\"distance\":\"9\"},{\"from\":\"Ciudad del Carmen\",\"to\":\"Veracruz\",\"distance\":\"18\"},{\"from\":\"Durango\",\"to\":\"Aguascalientes\",\"distance\":\"14\"},{\"from\":\"Saltillo\",\"to\":\"Veracruz\",\"distance\":\"23\"},{\"from\":\"Chihuahua\",\"to\":\"Merida\",\"distance\":\"85\"},{\"from\":\"Veracruz\",\"to\":\"Mazatlan\",\"distance\":\"58\"},{\"from\":\"Chihuahua\",\"to\":\"Tampico\",\"distance\":\"11\"},{\"from\":\"Manzanillo\",\"to\":\"Cancun\",\"distance\":\"18\"},{\"from\":\"Leon\",\"to\":\"La Paz\",\"distance\":\"85\"},{\"from\":\"Veracruz\",\"to\":\"Cancun\",\"distance\":\"27\"},{\"from\":\"Tampico\",\"to\":\"Veracruz\",\"distance\":\"1\"},{\"from\":\"Mazatlan\",\"to\":\"Cancun\",\"distance\":\"23\"},{\"from\":\"Puerto Escondido\",\"to\":\"Cancun\",\"distance\":\"20\"},{\"from\":\"Chihuahua\",\"to\":\"Leon\",\"distance\":\"27\"},{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Puebla\",\"distance\":\"37\"},{\"from\":\"Durango\",\"to\":\"Pachuca\",\"distance\":\"15\"},{\"from\":\"Zacatecas\",\"to\":\"Durango\",\"distance\":\"62\"},{\"from\":\"Pachuca\",\"to\":\"Cancun\",\"distance\":\"56\"},{\"from\":\"Tampico\",\"to\":\"Cancun\",\"distance\":\"12\"},{\"from\":\"Zacatecas\",\"to\":\"Cancun\",\"distance\":\"60\"},{\"from\":\"Queretaro\",\"to\":\"Veracruz\",\"distance\":\"56\"},{\"from\":\"Leon\",\"to\":\"San Luis Potosi\",\"distance\":\"10\"},{\"from\":\"Merida\",\"to\":\"Manzanillo\",\"distance\":\"42\"},{\"from\":\"Durango\",\"to\":\"Puerto Escondido\",\"distance\":\"15\"},{\"from\":\"La Paz\",\"to\":\"Merida\",\"distance\":\"21\"},{\"from\":\"Leon\",\"to\":\"Cancun\",\"distance\":\"5\"},{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Cancun\",\"distance\":\"37\"},{\"from\":\"Merida\",\"to\":\"Cancun\",\"distance\":\"65\"},{\"from\":\"Ciudad del Carmen\",\"to\":\"Manzanillo\",\"distance\":\"96\"},{\"from\":\"Chihuahua\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"91\"},{\"from\":\"Zacatecas\",\"to\":\"La Paz\",\"distance\":\"76\"},{\"from\":\"Acapulco\",\"to\":\"Cancun\",\"distance\":\"63\"},{\"from\":\"Tampico\",\"to\":\"Puebla\",\"distance\":\"51\"},{\"from\":\"Merida\",\"to\":\"Saltillo\",\"distance\":\"98\"},{\"from\":\"Tampico\",\"to\":\"Cuernavaca\",\"distance\":\"14\"},{\"from\":\"Puebla\",\"to\":\"Leon\",\"distance\":\"95\"},{\"from\":\"La Paz\",\"to\":\"Mazatlan\",\"distance\":\"55\"},{\"from\":\"San Luis Potosi\",\"to\":\"Zacatecas\",\"distance\":\"77\"},{\"from\":\"Cuernavaca\",\"to\":\"Monterrey\",\"distance\":\"23\"},{\"from\":\"Aguascalientes\",\"to\":\"Cancun\",\"distance\":\"64\"},{\"from\":\"Puebla\",\"to\":\"Acapulco\",\"distance\":\"79\"},{\"from\":\"Manzanillo\",\"to\":\"Tampico\",\"distance\":\"87\"},{\"from\":\"Tlaxcala\",\"to\":\"Cancun\",\"distance\":\"61\"},{\"from\":\"San Luis Potosi\",\"to\":\"Ensenada\",\"distance\":\"51\"},{\"from\":\"Puebla\",\"to\":\"Puerto Escondido\",\"distance\":\"87\"},{\"from\":\"Zacatecas\",\"to\":\"Queretaro\",\"distance\":\"31\"},{\"from\":\"San Luis Potosi\",\"to\":\"Tampico\",\"distance\":\"70\"},{\"from\":\"Monterrey\",\"to\":\"Cancun\",\"distance\":\"90\"},{\"from\":\"Saltillo\",\"to\":\"Tlaxcala\",\"distance\":\"69\"},{\"from\":\"Tepic\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"72\"},{\"from\":\"Leon\",\"to\":\"Tepic\",\"distance\":\"5\"},{\"from\":\"Cuernavaca\",\"to\":\"Puerto Escondido\",\"distance\":\"38\"},{\"from\":\"Leon\",\"to\":\"Ciudad del Carmen\",\"distance\":\"9\"},{\"from\":\"Chihuahua\",\"to\":\"Zacatecas\",\"distance\":\"97\"},{\"from\":\"Veracruz\",\"to\":\"Puerto Escondido\",\"distance\":\"54\"}]";
            route = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, RouteList.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException("Can't get the Route path. Service is unavailable. " +
                    "Please contact your administrator or jump out the window.");
        }
        LOGGER.info("Route path generated successfully.");

        return route;

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
