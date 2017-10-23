package org.spring.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.dataflow.rest.client.TaskOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(properties = "dataflow-server-uri=http://foo/bar", webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableConfigurationProperties(TaskLauncherProperties.class)
@RunWith(SpringRunner.class)
public class SimpleTaskLauncherSinkApplicationTests {
	@MockBean
	private TaskOperations taskOperations;

	@Autowired
	private TaskLauncherProperties properties;


	@Test
	public void contextLoads() {
		assertThat(properties.getDataflowServerUri().toString()).isEqualTo("http://foo/bar");
	}

}
