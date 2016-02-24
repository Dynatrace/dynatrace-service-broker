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

package com.ruxit.cf.servicebroker.catalog;

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

  public static Map<String,RuxitPlan> plans = null;

  public static RuxitPlan getAssociatedPlanById(String ruxitPlanId) {
    if (plans == null) {
      return null;
    }
    for (RuxitPlan p : plans.values()) {
      System.out.println("\t\tRuxitPlan: " + p + ", checking for planId: " + ruxitPlanId);
      if (p.getPlanId().equals(ruxitPlanId)) {
        return p;
      }
    }
    return null;
  }

  public static RuxitPlan getAssociatedPlanByName(String ruxitPlanName) {
    if (plans == null) {
      return null;
    }
    for (RuxitPlan p : plans.values()) {
      System.out.println("\t\tRuxitPlan: " + p + ", checking for planname: " + ruxitPlanName);
      if (p.getName().equals(ruxitPlanName)) {
        return p;
      }
    }
    return null;
  }
  @Bean
  Catalog catalog(@Value("${PLANS}") String ruxitPlans)
    throws Exception
  {
    System.out.println("PLANS set to: " + ruxitPlans);
    String serviceId = UUID.nameUUIDFromBytes("Ruxit_ServiceId_v1".getBytes()).toString();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    plans = (Map)objectMapper.readValue(ruxitPlans, objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, RuxitPlan.class));

    for (RuxitPlan p : plans.values()) {
      p.setPlanId();
      System.out.println("\t\tRuxitPlan: " + p);
    }

    return new Catalog()
                    .service()
                          .id(UUID.fromString(serviceId))
                          .name("ruxit")
                          .description("Ruxit is all-in-one full stack performance monitoring and management powered by artificial intelligence")
                          .bindable(Boolean.valueOf(true))
                          .tags(new String[] { "ruxit", "performance", "monitoring", "apm", "analytics" })
                          .metadata()
                              .displayName("Ruxit")
                              .imageUrl(URI.create("https://ruxit.com/images/ruxit_signet.jpg"))
                              .longDescription("Ruxit is all-in-one full stack performance " +
                                   "monitoring and management powered by artificial " +
                                   "intelligence that provides you with automated application-health " +
                                   "and root-cause analysis information to quickly identify " +
                                   "performance bottlenecks in browsers, databases and code.")
                              .providerDisplayName("Dynatrace LLC")
                              .documentationUrl(URI.create("https://help.ruxit.com"))
                              .supportUrl(URI.create("https://support.ruxit.com"))
                              .and()
                          .addAllPlans(plans)
                          .and();
  }
}
