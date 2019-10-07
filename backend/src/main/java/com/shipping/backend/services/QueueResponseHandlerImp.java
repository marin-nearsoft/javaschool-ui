package com.shipping.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.*;
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
    private QueueRequestMessage queueRequestMessage = new QueueRequestMessage();
    private RoutesResponseProcessor routesResponseProcessor = new RoutesResponseProcessor();

    public QueueResponseHandlerImp(final QueueClient shippingRequestSender,
                                   final AppConfiguration appConfiguration,
                                   final ObjectMapper mapper) {
        this.shippingRequestSender = shippingRequestSender;
        this.appConfiguration = appConfiguration;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getTypes() {

        queueRequestMessage.setType(appConfiguration.getPackageTypes());
        LOG.info("Generating package type list");
        try {
            List<PackageType> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class));
            LOG.info("Package type list successfully generated");
            return packageTypes;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<PackageSize> getSizes() {

        queueRequestMessage.setType(appConfiguration.getPackageSizes());
        LOG.info("Generating package size list");
        try {
            List<PackageSize> packageSizes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageSize.class));
            LOG.info("Package size list successfully generated");
            return packageSizes;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<Transport> getTransports() {

        queueRequestMessage.setType(appConfiguration.getTransportTypes());
        LOG.info("Generating transport types list");
        try {
            List<Transport> transports = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, Transport.class));
            LOG.info("Transports list successfully generated");
            return transports;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List<TransportVelocity> getTransportVelocity() {

        queueRequestMessage.setType(appConfiguration.getTransportVelocity());
        LOG.info("Generating transport velocity list");
        try {
            List<TransportVelocity> transportVelocities = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, TransportVelocity.class));
            LOG.info("Transport velocities list successfully generated");
            return transportVelocities;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List getCities() {

        queueRequestMessage.setType(appConfiguration.getCities());
        LOG.info("Generating cities list");
        try {
            List<City> cities = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, City.class));
            LOG.info("City list successfully generated");
            return cities;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List getRoutes() {

        queueRequestMessage.setType(appConfiguration.getRouteList());
        queueRequestMessage.setOrigin("Chihuahua");
        queueRequestMessage.setDestination("Cancun");
        log.info("Generating routes list");
        try {
            List<Route> routes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, Route.class));
            HashMap<String, CityVertex> cityVertexMap = routesResponseProcessor.getRoutesMap(routes);
            routesResponseProcessor.computeShortestPaths(cityVertexMap.get(queueRequestMessage.getOrigin()));
            log.info("Generating routes list" + routesResponseProcessor.getShortestPathTo(cityVertexMap.get(queueRequestMessage.getDestination())));
            return routesResponseProcessor.getShortestPathTo(cityVertexMap.get(queueRequestMessage.getDestination()));

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

}