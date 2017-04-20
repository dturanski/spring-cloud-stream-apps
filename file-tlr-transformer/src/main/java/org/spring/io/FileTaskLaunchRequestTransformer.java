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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.Collections;
/**
 * @author David Turanski
 **/
@EnableBinding(Processor.class)
@EnableConfigurationProperties(FileTlrProperties.class)
public class FileTaskLaunchRequestTransformer {

	@Autowired
	private FileTlrProperties properties;

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public SimpleTaskLaunchRequest supplyFileName(String payload) {

		return new SimpleTaskLaunchRequest(properties.getTaskName(), properties.getArgs(),
				 Collections.singletonMap(
						 String.join(".","app",properties.getTaskName(),"fileName"),
						 payload));
	}
}
