package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.PackageSize;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.QueueRequestMessage;
import com.shipping.backend.entities.Transport;
import com.shipping.backend.services.QueueResponseHandler;
import com.shipping.backend.services.QueueResponseHandlerImp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BackendApplicationTests {

    private  RabbitTemplate rabbitTemplate;
    private  QueueResponseHandler queueResponseHandler;
    private  AppConfiguration appConfiguration;
    private  QueueRequestMessage queueRequestMessage;

    @Before
    public void setUp(){

        //Initialize functional classes for testing
        queueRequestMessage = new QueueRequestMessage();
        appConfiguration = new AppConfiguration();
        rabbitTemplate = mock(RabbitTemplate.class);
        ObjectMapper mapper = new ObjectMapper();
        QueueClient shippingRequestSender = new QueueClient(rabbitTemplate);
        queueResponseHandler = new QueueResponseHandlerImp(shippingRequestSender, appConfiguration, mapper);

    }

	@Test
	public void getPackageTypesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("packageType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");

        //Mocked Response Values
        PackageType packageType = new PackageType();
        packageType.setId(1);
        packageType.setDescription("Box");
        packageType.setPrice(100);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                packageType.toString());
        List packageTypesList = queueResponseHandler.getTypes();

        assertEquals(packageTypesList.size(),1);
        assertEquals(packageTypesList.get(0).getClass(), PackageType.class);
        assertThat(packageTypesList.get(0), hasProperty("description", is("Box")));

	}

    @Test(expected = CustomException.class)
    public void getPackageTypesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("packageType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        queueResponseHandler.getTypes();

    }

    @Test
    public void getPackageSizesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("packageSize");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageSizes("packageSize");

        //Mocked Response Values
        PackageSize packageSize = new PackageSize();
        packageSize.setId(1);
        packageSize.setDescription("Small");
        packageSize.setPriceFactor(5);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                packageSize.toString());
        List packageSizesList = queueResponseHandler.getSizes();

        assertEquals(packageSizesList.size(),1);
        assertEquals(packageSizesList.get(0).getClass(), PackageSize.class);
        assertThat(packageSizesList.get(0), hasProperty("description", is("Small")));

    }

    @Test(expected = CustomException.class)
    public void getPackageSizesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("packageSize");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageSizes("packageSize");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        queueResponseHandler.getSizes();

    }

    @Test
    public void getTransportTypesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("transportType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportTypes("transportType");

        //Mocked Response Values
        Transport transportType = new Transport();
        transportType.setId(2);
        transportType.setDescription("Land");
        transportType.setPricePerMile(2);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                transportType.toString());
        List transportTypesList = queueResponseHandler.getTransports();

        assertEquals(transportTypesList.size(),1);
        assertEquals(transportTypesList.get(0).getClass(), Transport.class);
        assertThat(transportTypesList.get(0), hasProperty("description", is("Land")));

    }

    @Test(expected = CustomException.class)
    public void getTransportTypesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("transportType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportTypes("transportType");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List transportTypesList = queueResponseHandler.getTransports();

    }

}
