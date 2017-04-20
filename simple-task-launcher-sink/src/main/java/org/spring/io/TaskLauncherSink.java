package org.spring.io;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.dataflow.rest.client.DataFlowTemplate;
import org.springframework.cloud.dataflow.rest.client.TaskOperations;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

@EnableBinding(Sink.class)
@EnableConfigurationProperties(TaskLauncherProperties.class)
public class TaskLauncherSink {
	Log logger = LogFactory.getLog(TaskLauncherSink.class);

	@Autowired
	private TaskLauncherProperties properties;
	
	@Autowired 
	private TaskOperations taskOperations;
	
	@Bean
	public TaskOperations taskOperations() {
		DataFlowTemplate dataFlowTemplate = new DataFlowTemplate(
				this.properties.getDataflowServerUri());

		return dataFlowTemplate.taskOperations();
	}
	
	@StreamListener(Sink.INPUT)
	public void launchTask(SimpleTaskLaunchRequest request){
		long executionId = this.taskOperations.launch(request.getTaskName(),
				request.getProperties(), request.getArgs());
		logger.info(String.format("Launched task %s with execution id: %d",request.getTaskName(), executionId));
	}
}
