package com.javaschool.queue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.dijkstra.RouteList;
import com.javaschool.modelmapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class QueueResponseServiceImp implements QueueResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderServiceImp.class);

    private QueueSenderService queueSenderService;
    private ObjectMapper mapper;
    private TypeReference refpackage = new TypeReference<List<PackageType>>() {};
    private TypeReference refsize = new TypeReference<List<PackageSize>>() {};
    private TypeReference reftransport = new TypeReference<List<TransportType>>() {};
    private TypeReference refvelocities = new TypeReference<List<TransportVelocity>>() {};
    private TypeReference refcities = new TypeReference<List<Cities>>() {};
    private TypeReference refroute = new TypeReference<List<RouteList>>() {};


    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService = queueSenderService;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types = Collections.emptyList();
        try {
//            String response = queueSenderService.sendRequest("packageType", null, null);
            String response = "[{\"id\":2,\"description\":\"Box\",\"price\":10}]";
            types = mapper.readValue(response, refpackage);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return types;
    }

    @Override
    public List<PackageSize> getSize() {
        List<PackageSize> sizes = Collections.emptyList();
        try {
//            String response = queueSenderService.sendRequest("packageSize", null, null);
            String response = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5}]";
            sizes = mapper.readValue(response, refsize);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return sizes;
    }

    @Override
    public List<TransportType> getTransport() {
        List<TransportType> transports = Collections.emptyList();
        try {
//            String response = queueSenderService.sendRequest("transportType", null, null);
            String response = "[{\"id\":2,\"description\":\"Land\",\"pricePerMile\":2}]";
            transports = mapper.readValue(response, reftransport);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return transports;
    }

    @Override
    public List<TransportVelocity> getVelocity() {
        List<TransportVelocity> velocities = Collections.emptyList();
        try {
//            String response = queueSenderService.sendRequest("transportVelocity", null, null);
            String response = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5}]";
            velocities = mapper.readValue(response, refvelocities);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return velocities;
    }

    @Override
    public List<Cities> getCity() {
        List<Cities> cities = Collections.emptyList();
        try {
//            String response = queueSenderService.sendRequest("city", null, null);
            String response = "[{\"id\":1,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":2,\"name\":\"Chihuahua\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":3,\"name\":\"Aguascalientes\",\"tax\":10,\"seaport\":false,\"airport\":false}]";

            cities = mapper.readValue(response, refcities);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return cities;
    }

    @Override
    public List<RouteList> getRoute(String origin, String destination) {
        List<RouteList> route = Collections.emptyList();
        try {
            //String response = queueSenderService.sendRequest("routesList", origin, destination);
            String response = "[{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Durango\",\"distance\":\"58\"},{\"from\":\"Tampico\",\"to\":\"San Luis Potosi\",\"distance\":\"96\"},{\"from\":\"Puebla\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"9\"},{\"from\":\"Ensenada\",\"to\":\"Cancun\",\"distance\":\"55\"},{\"from\":\"La Paz\",\"to\":\"Ensenada\",\"distance\":\"9\"},{\"from\":\"Ciudad del Carmen\",\"to\":\"Veracruz\",\"distance\":\"18\"},{\"from\":\"Durango\",\"to\":\"Aguascalientes\",\"distance\":\"14\"},{\"from\":\"Saltillo\",\"to\":\"Veracruz\",\"distance\":\"23\"},{\"from\":\"Chihuahua\",\"to\":\"Merida\",\"distance\":\"85\"},{\"from\":\"Veracruz\",\"to\":\"Mazatlan\",\"distance\":\"58\"},{\"from\":\"Chihuahua\",\"to\":\"Tampico\",\"distance\":\"11\"},{\"from\":\"Manzanillo\",\"to\":\"Cancun\",\"distance\":\"18\"},{\"from\":\"Leon\",\"to\":\"La Paz\",\"distance\":\"85\"},{\"from\":\"Veracruz\",\"to\":\"Cancun\",\"distance\":\"27\"},{\"from\":\"Tampico\",\"to\":\"Veracruz\",\"distance\":\"1\"},{\"from\":\"Mazatlan\",\"to\":\"Cancun\",\"distance\":\"23\"},{\"from\":\"Puerto Escondido\",\"to\":\"Cancun\",\"distance\":\"20\"},{\"from\":\"Chihuahua\",\"to\":\"Leon\",\"distance\":\"27\"},{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Puebla\",\"distance\":\"37\"},{\"from\":\"Durango\",\"to\":\"Pachuca\",\"distance\":\"15\"},{\"from\":\"Zacatecas\",\"to\":\"Durango\",\"distance\":\"62\"},{\"from\":\"Pachuca\",\"to\":\"Cancun\",\"distance\":\"56\"},{\"from\":\"Tampico\",\"to\":\"Cancun\",\"distance\":\"12\"},{\"from\":\"Zacatecas\",\"to\":\"Cancun\",\"distance\":\"60\"},{\"from\":\"Queretaro\",\"to\":\"Veracruz\",\"distance\":\"56\"},{\"from\":\"Leon\",\"to\":\"San Luis Potosi\",\"distance\":\"10\"},{\"from\":\"Merida\",\"to\":\"Manzanillo\",\"distance\":\"42\"},{\"from\":\"Durango\",\"to\":\"Puerto Escondido\",\"distance\":\"15\"},{\"from\":\"La Paz\",\"to\":\"Merida\",\"distance\":\"21\"},{\"from\":\"Leon\",\"to\":\"Cancun\",\"distance\":\"5\"},{\"from\":\"Tuxtla Gutierrez\",\"to\":\"Cancun\",\"distance\":\"37\"},{\"from\":\"Merida\",\"to\":\"Cancun\",\"distance\":\"65\"},{\"from\":\"Ciudad del Carmen\",\"to\":\"Manzanillo\",\"distance\":\"96\"},{\"from\":\"Chihuahua\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"91\"},{\"from\":\"Zacatecas\",\"to\":\"La Paz\",\"distance\":\"76\"},{\"from\":\"Acapulco\",\"to\":\"Cancun\",\"distance\":\"63\"},{\"from\":\"Tampico\",\"to\":\"Puebla\",\"distance\":\"51\"},{\"from\":\"Merida\",\"to\":\"Saltillo\",\"distance\":\"98\"},{\"from\":\"Tampico\",\"to\":\"Cuernavaca\",\"distance\":\"14\"},{\"from\":\"Puebla\",\"to\":\"Leon\",\"distance\":\"95\"},{\"from\":\"La Paz\",\"to\":\"Mazatlan\",\"distance\":\"55\"},{\"from\":\"San Luis Potosi\",\"to\":\"Zacatecas\",\"distance\":\"77\"},{\"from\":\"Cuernavaca\",\"to\":\"Monterrey\",\"distance\":\"23\"},{\"from\":\"Aguascalientes\",\"to\":\"Cancun\",\"distance\":\"64\"},{\"from\":\"Puebla\",\"to\":\"Acapulco\",\"distance\":\"79\"},{\"from\":\"Manzanillo\",\"to\":\"Tampico\",\"distance\":\"87\"},{\"from\":\"Tlaxcala\",\"to\":\"Cancun\",\"distance\":\"61\"},{\"from\":\"San Luis Potosi\",\"to\":\"Ensenada\",\"distance\":\"51\"},{\"from\":\"Puebla\",\"to\":\"Puerto Escondido\",\"distance\":\"87\"},{\"from\":\"Zacatecas\",\"to\":\"Queretaro\",\"distance\":\"31\"},{\"from\":\"San Luis Potosi\",\"to\":\"Tampico\",\"distance\":\"70\"},{\"from\":\"Monterrey\",\"to\":\"Cancun\",\"distance\":\"90\"},{\"from\":\"Saltillo\",\"to\":\"Tlaxcala\",\"distance\":\"69\"},{\"from\":\"Tepic\",\"to\":\"Tuxtla Gutierrez\",\"distance\":\"72\"},{\"from\":\"Leon\",\"to\":\"Tepic\",\"distance\":\"5\"},{\"from\":\"Cuernavaca\",\"to\":\"Puerto Escondido\",\"distance\":\"38\"},{\"from\":\"Leon\",\"to\":\"Ciudad del Carmen\",\"distance\":\"9\"},{\"from\":\"Chihuahua\",\"to\":\"Zacatecas\",\"distance\":\"97\"},{\"from\":\"Veracruz\",\"to\":\"Puerto Escondido\",\"distance\":\"54\"}]";
            route = mapper.readValue(response, refroute);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return route;

    }
}
