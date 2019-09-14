package com.JavaSchool.BackEnd;

import static org.mockito.Mockito.*;

import java.util.List;

import com.JavaSchool.EntityMapper.MessageType;
import com.JavaSchool.EntityMapper.PackageType;
import com.JavaSchool.Queue.QueueClient;
import com.JavaSchool.Service.BackEndServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackEndApplicationTests {


    private RabbitTemplate rabbitTemplatemock;
    private BackEndServiceImp backServiceImp;
    private ObjectMapper mapper= new ObjectMapper();
	private static MessageType messageType = new MessageType();
	

    @Before
    public void setup() {
        rabbitTemplatemock = mock(RabbitTemplate.class);
        QueueClient queueClient = new QueueClient(rabbitTemplatemock, mapper);
        backServiceImp =  new BackEndServiceImp(queueClient);
    }


    @Test
    public void getTypeTest() throws JsonProcessingException {
		messageType.setType("packageType");

		PackageType packagetyperesponse = new PackageType();
		packagetyperesponse.setId(1);
		packagetyperesponse.setDescription("Box");
		packagetyperesponse.setPrice(10);
		String expected = "{\"id\":1,\"description\":\"Box\",\"price\":10}";

		String message = mapper.writeValueAsString(messageType);
		String rabbitresponse = mapper.writeValueAsString(packagetyperesponse);

        when(rabbitTemplatemock.convertSendAndReceive(message)).thenReturn(rabbitresponse);
        List<PackageType> types = backServiceImp.getType();

        String actual = (String) rabbitTemplatemock.convertSendAndReceive(message);
        Assert.assertEquals(expected, actual);
    }

}
