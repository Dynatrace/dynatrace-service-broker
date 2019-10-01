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

/**
 * A holder for credential information such as the environmentid, apitoken|tenanttoken, endpoint
 */
public final class Credentials {

  private String environmentid;
  private String apitoken;
  private String tenanttoken;
  private String endpoint;

  public Credentials(String environmentid, String apitoken, String tenanttoken, String endpoint) {
    this.environmentid = environmentid;
    this.apitoken = apitoken;
    this.tenanttoken = tenanttoken;
    this.endpoint = endpoint;
  }

  public String getEnvironmentid() throws Exception {
    return this.environmentid;
  }

  public String getTenanttoken() throws Exception {
    return this.tenanttoken;
  }

  public String getApitoken() {
    return this.apitoken;
  }

  public String getEndpoint() throws Exception {
    return endpoint;
  }

}
