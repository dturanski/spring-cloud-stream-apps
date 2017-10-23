package org.spring.io;

import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.dataflow.rest.client.TaskOperations;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskLauncherTests {
	@Autowired
	private MessageChannel input;


	@MockBean
	private TaskOperations taskOperations;


	@Autowired 
	private ObjectMapper objectMapper;


	//TODO Mock TaskOperations
	@Ignore
	@Test public void testSimpleLaunch() throws JsonProcessingException {

		SimpleTaskLaunchRequest request = new SimpleTaskLaunchRequest("hello-world-task",
				null,
				null);
		String json = objectMapper.writeValueAsString(request);
		Message<?> message = MessageBuilder.withPayload(json)
				.setHeader(MessageHeaders.CONTENT_TYPE,"application/json")
				.build();
		input.send(message);
		verify(taskOperations.launch(request.getTaskName(), request.getProperties(), request.getArgs()));
	}
}
