/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dynatrace.cf.servicebroker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.json.JsonMapper;

/**
 * Main application entry point and configuration
 */
@SpringBootApplication
public class Application {

    /**
     * Start method
     *
     * @param args command line argument
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    FilterRegistrationBean<BrokerApiVersionFilter> brokerApiVersionFilter() {
        FilterRegistrationBean<BrokerApiVersionFilter> bean = new FilterRegistrationBean<>(new BrokerApiVersionFilter());
        bean.addUrlPatterns("/v2/*");

        return bean;
    }

    @Bean
    JsonMapper jsonMapper() {
        return JsonMapper.builder()
                .changeDefaultVisibility(v -> v.withVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY))
                .changeDefaultPropertyInclusion(i -> i.withValueInclusion(JsonInclude.Include.NON_NULL))
                .build();
    }

}
