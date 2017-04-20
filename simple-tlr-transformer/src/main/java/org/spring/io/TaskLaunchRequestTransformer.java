/*
 *
 *   Copyright 2017 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *
 */

package org.spring.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.Collections;

/**
 * @author David Turanski
 **/
@EnableBinding(Processor.class)
public class TaskLaunchRequestTransformer {

	@Value("${propertyName:val}")
	private String propertyName;
	@Value ("${taskName}")
	private String taskName;

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public SimpleTaskLaunchRequest transform(String payload) {

		SimpleTaskLaunchRequest simpleTaskLaunchRequest = new SimpleTaskLaunchRequest
				(taskName, null, Collections.singletonMap(propertyName, payload));
		return simpleTaskLaunchRequest;
	}

}
