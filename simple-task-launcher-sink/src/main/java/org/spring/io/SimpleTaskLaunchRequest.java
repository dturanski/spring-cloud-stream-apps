package org.spring.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

 

@JsonInclude(Include.NON_EMPTY)
public class SimpleTaskLaunchRequest {

	private final String taskName;

	private List<String> args = new ArrayList<>();

	private Map<String, String> properties = new HashMap<>();

	@JsonCreator
	public SimpleTaskLaunchRequest(@JsonProperty("taskName") String taskName, @JsonProperty("args") List<String> args,
			@JsonProperty("properties") Map<String, String> properties) {
		Assert.hasText(taskName,"'taskName cannot be empty.");
		this.taskName = taskName;
		if (args != null) {
			this.args = args ;
		}
		if (properties!= null) {
			this.properties = properties;
		}
	}

	public String getTaskName() {
		return taskName;
	}

	public List<String> getArgs() {
		return args;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

}
