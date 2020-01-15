/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jbariel.example.springboot.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.h2.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Jarrett Bariel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

	/**
	 * Simulate our mock object
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Check default behavior in {@link HelloWorldController#sayHello()}
	 * 
	 * @throws Exception if things go horribly wrong
	 *
	 */
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		checkNamedParam(null);
	}

	/**
	 * Check behavior of {@link HelloWorldController#sayHello(String)}
	 * 
	 * @throws Exception if things go horribly wrong
	 * 
	 * @see #checkNamedParam(String)
	 *
	 */
	@Test
	public void shouldReturnNamedMessageByParameter() throws Exception {
		checkNamedParam("Jarrett");
		checkNamedParam("Bob");
		checkNamedParam("frank");
		checkNamedParam("joey the nose");
	}

	/**
	 * 
	 * keep code DRY
	 * 
	 * @param param String to send as a param and check output - use {@code null} to
	 *              test defaultMessage
	 * @throws Exception if things go horribly wrong
	 *
	 */
	protected void checkNamedParam(final String param) throws Exception {
		String output = (StringUtils.isNullOrEmpty(param)) ? "World" : param;
		this.mockMvc.perform(get("/hello/" + (null == param ? "" : param))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello " + output + "!")));
	}
}
