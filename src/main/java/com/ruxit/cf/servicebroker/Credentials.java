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

package com.ruxit.cf.servicebroker;

import java.util.HashMap;

/**
 * A holder for credential information such as the tenant, tenanttoken, server
 */
public final class Credentials {

  private String tenant;
  private String tenanttoken;
  private String server;

  public Credentials(String tenant, String tenanttoken, String server) {
    this.tenant = tenant;
    this.tenanttoken = tenanttoken;
    this.server = server;
  }

  public String getTenant() throws Exception {
    return this.tenant;
  }

  public String getTenanttoken() throws Exception {
    return this.tenanttoken;
  }

  public String getServer() throws Exception {
    return server;
  }

}
