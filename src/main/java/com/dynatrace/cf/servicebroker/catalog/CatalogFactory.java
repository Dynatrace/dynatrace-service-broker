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

package com.dynatrace.cf.servicebroker.catalog;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CatalogFactory {

  public static Map<String,DynatracePlan> plans = null;

  public static DynatracePlan getAssociatedPlanById(String dynatracePlanId) {
    if (plans == null) {
      return null;
    }
    for (DynatracePlan p : plans.values()) {
      System.out.println("\t\tDynatracePlan: " + p + ", checking for planId: " + dynatracePlanId);
      if (p.getPlanId().equals(dynatracePlanId)) {
        return p;
      }
    }
    return null;
  }

  public static DynatracePlan getAssociatedPlanByName(String dynatracePlanName) {
    if (plans == null) {
      return null;
    }
    for (DynatracePlan p : plans.values()) {
      System.out.println("\t\tDynatracePlan: " + p + ", checking for planname: " + dynatracePlanName);
      if (p.getName().equals(dynatracePlanName)) {
        return p;
      }
    }
    return null;
  }
  @Bean
  Catalog catalog(@Value("${DYNATRACE_SERVICE_PLANS}") String dynatracePlans)
    throws Exception
  {
    System.out.println("DYNATRACE_SERVICE_PLANS set to: " + dynatracePlans);
    String serviceId = UUID.nameUUIDFromBytes("Dynatrace_ServiceId_v12".getBytes()).toString();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    plans = (Map)objectMapper.readValue(dynatracePlans, objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, DynatracePlan.class));

    for (DynatracePlan p : plans.values()) {
      p.setPlanId();
      System.out.println("\t\tDynatracePlan: " + p);
    }

    return new Catalog()
                    .service()
                          .id(UUID.fromString(serviceId))
                          .name("dynatrace")
                          .description("Dynatrace is all-in-one full stack performance monitoring and management powered by artificial intelligence")
                          .bindable(Boolean.valueOf(true))
                          .tags(new String[] { "dynatrace", "performance", "monitoring", "apm", "analytics" })
                          .metadata()
                              .displayName("Dynatrace")
                              .imageUrl(URI.create("https://assets.dynatrace.com/global/resources/Signet_Logo_RGB_CP_48x48px.png"))
                              .longDescription("Dynatrace is all-in-one full stack performance " +
                                   "monitoring and management powered by artificial " +
                                   "intelligence that provides you with automated application-health " +
                                   "and root-cause analysis information to quickly identify " +
                                   "performance bottlenecks in browsers, databases and code.")
                              .providerDisplayName("Dynatrace LLC")
                              .documentationUrl(URI.create("https://help.dynatrace.com"))
                              .supportUrl(URI.create("https://support.ruxit.com"))
                              .and()
                          .addAllPlans(plans)
                          .and();
  }
}
