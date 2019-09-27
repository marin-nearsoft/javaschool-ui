package com.nearsoft.javaschoolbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.javaschoolbackend.config.ConfigProperties;
import com.nearsoft.javaschoolbackend.exception.custom.CentralServerException;
import com.nearsoft.javaschoolbackend.exception.custom.PackageDataException;
import com.nearsoft.javaschoolbackend.model.request.TypeRequest;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.service.impl.PackageServiceImpl;
import com.nearsoft.javaschoolbackend.util.RabbitMQSender;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PackageServiceTests {

    private PackageService packageService;

    private RabbitMQSender rabbitMQSender;

    private RabbitTemplate rabbitTemplate;

    @Before
    public void initUseCase(){
        rabbitTemplate = mock(RabbitTemplate.class);
        rabbitMQSender = new RabbitMQSender(rabbitTemplate, new ConfigProperties());
        packageService = new PackageServiceImpl(rabbitMQSender, new ObjectMapper());
    }

    @Test
    public void testGetPackageTypes() throws JsonProcessingException {
        List<PackageType> packageTypes = new ArrayList<PackageType>();
        PackageType packageTypeOne = new PackageType(2, "Box", 10);
        PackageType packageTypeTwo = new PackageType(3, "Envelope", 5);
        packageTypes.add(packageTypeOne);
        packageTypes.add(packageTypeTwo);

        when(rabbitTemplate.convertSendAndReceive(null, null, new TypeRequest("packageType").toJSONString())).thenReturn("[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]");
        assertEquals(packageTypes, packageService.getPackageTypes());
    }

    @Test(expected = PackageDataException.class)
    public void testPackageTypesDataException() throws JsonProcessingException {
        when(rabbitTemplate.convertSendAndReceive(null, null, new TypeRequest("packageType").toJSONString())).thenReturn(null);
        packageService.getPackageTypes();
    }

    @Test(expected = CentralServerException.class)
    public void testPackageTypesCentralServerException() throws JsonProcessingException {
        when(rabbitTemplate.convertSendAndReceive(null, null, new TypeRequest("packageType").toJSONString())).thenThrow(CentralServerException.class);
        packageService.getPackageTypes();
    }


}
