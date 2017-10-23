package org.spring.io;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class TaskLauncherProperties {
	/**
	 * The URI for the dataflow server that will receive task launch requests. Default is http://localhost:9393;
	 */
	private String dataflowServerUri = "http://localhost:9393";

	public void setDataflowServerUri(String uri) {
		this.dataflowServerUri = uri;
	}
	
	public URI getDataflowServerUri() {
		try {
			return new URI(this.dataflowServerUri);
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Invalid Spring Cloud Data Flow Server URI", e);
		}
	}
}
