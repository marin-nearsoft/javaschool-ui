package com.shipping.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.PackageSize;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.QueueRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueResponseHandlerImp implements QueueResponseHandler {

    private final static Logger log = LoggerFactory.getLogger(QueueResponseHandlerImp.class);

    private QueueClient shippingRequestSender;
    private ObjectMapper mapper;
    private AppConfiguration appConfiguration;
    private QueueRequestMessage queueRequestMessage = new QueueRequestMessage();

    public QueueResponseHandlerImp(final QueueClient shippingRequestSender,
                                   final AppConfiguration appConfiguration,
                                   final ObjectMapper mapper){
        this.shippingRequestSender=shippingRequestSender;
        this.appConfiguration=appConfiguration;
        this.mapper=mapper;
    }

    @Override
    public List getTypes() {

        queueRequestMessage.setType(appConfiguration.getPackageTypes());
        log.info("Generating package type list");
        try {
            List<PackageType> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class));
            log.info("Package type list successfully generated");
            return packageTypes;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

    @Override
    public List getSizes() {

        queueRequestMessage.setType(appConfiguration.getPackageSizes());
        log.info("Generating package size list");
        try {
            List<PackageSize> packageSizes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(queueRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageSize.class));
            log.info("Package type list successfully generated");
            return packageSizes;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("Service not available, please contact your administrator");
        }
    }

}
