package com.shipping.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.models.common.*;
import com.shipping.backend.models.dijkstra.CityVertex;
import com.shipping.backend.models.rmq.QueueRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QueueResponseHandlerImp implements QueueResponseHandler {

    private final static Logger LOG = LoggerFactory.getLogger(QueueResponseHandlerImp.class);

    private QueueClient shippingRequestSender;
    private ObjectMapper mapper;
    private AppConfiguration appConfiguration;
    private ResponseList responseList;
    private RoutesResponseProcessor routesResponseProcessor = new RoutesResponseProcessor();

    public QueueResponseHandlerImp(final QueueClient shippingRequestSender,
                                   final AppConfiguration appConfiguration,
                                   final ResponseList responseList,
                                   final ObjectMapper mapper) {
        this.shippingRequestSender = shippingRequestSender;
        this.appConfiguration = appConfiguration;
        this.responseList = responseList;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getTypes() {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getPackageTypes());
        LOG.info("Generating package type list");
        try {
            responseList.setPackageTypes(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class)));
            LOG.info("Package type list successfully generated");
            return responseList.getPackageTypes();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<PackageSize> getSizes() {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getPackageSizes());
        LOG.info("Generating package size list");
        try {
            responseList.setPackageSizes(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageSize.class)));
            LOG.info("Package size list successfully generated");
            return responseList.getPackageSizes();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<Transport> getTransports() {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getTransportTypes());
        LOG.info("Generating transport types list");
        try {
            responseList.setTransports(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, Transport.class)));
            LOG.info("Transports list successfully generated");
            return responseList.getTransports();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<TransportVelocity> getTransportVelocity() {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getTransportVelocity());
        LOG.info("Generating transport velocity list");
        try {
            responseList.setTransportVelocities(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, TransportVelocity.class)));
            LOG.info("Transport velocities list successfully generated");
            return responseList.getTransportVelocities();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List getCities() {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getCities());
        LOG.info("Generating cities list");
        try {
            responseList.setCities(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, City.class)));
            LOG.info("City list successfully generated");
            return responseList.getCities();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<String> getRoutes(String origin, String destination) {
        QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
        queueRequestMessage.setType(appConfiguration.getRouteList());
        queueRequestMessage.setOrigin(origin);
        queueRequestMessage.setDestination(destination);
        LOG.info("Generating routes list");
        try {
            responseList.setRoutes(mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, Route.class)));
            HashMap<String, CityVertex> cityVertexMap = routesResponseProcessor.getRoutesMap(responseList.getRoutes());
            routesResponseProcessor.computeShortestPaths(cityVertexMap.get(queueRequestMessage.getOrigin()));
            LOG.info("Generating routes list" + routesResponseProcessor.getShortestPathTo(cityVertexMap.get(queueRequestMessage.getDestination())));
            return routesResponseProcessor.getShortestPathTo(cityVertexMap.get(queueRequestMessage.getDestination()));

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }


    @Override
    public double getPrice(String size, String type, String time, String transport) {

        double typePrice = responseList.getPackageTypes().stream().filter(currentype -> type.equals(currentype.getDescription()))
                .mapToDouble(PackageType::getPrice).sum();

        double sizePrice = responseList.getPackageSizes().stream().filter(currensize -> size.equals(currensize.getDescription()))
                .mapToDouble(PackageSize::getPriceFactor).sum();

        double transportPrice = responseList.getTransports().stream().filter(currenttransport -> transport.equals(currenttransport.getDescription()))
                .mapToDouble(Transport::getPricePerMile).sum();

        double velocityPrice = responseList.getTransportVelocities().stream().filter(currentvelocity -> time.equals(currentvelocity.getDescription()))
                .mapToDouble(TransportVelocity::getPriceFactor).sum();

        return (typePrice * (sizePrice / 100)) + (transportPrice * (velocityPrice / 100));
    }

    @Override
    public List<ShipmentInformation> getInformation() {
        //This code is just for testing purpose
        String mockValues = "[{\"folio\":\"00598\",\"path\":\"Chihuahua, Tampico, Puebla, Tuxtla Gutierrez, Durango, Aguascalientes\",\"price\":\"29.18\"},{\"folio\":\"00666\",\"path\":\"Sonora, Sinaloa\",\"price\":\"99566.6\"}]";

        try {
            return mapper.readValue(mockValues, mapper.getTypeFactory().constructCollectionType(List.class, ShipmentInformation.class));

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }

    }


}
