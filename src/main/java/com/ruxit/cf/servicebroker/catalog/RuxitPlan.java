package com.ruxit.cf.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuxitPlan {
    private String name = null;
    private String tenant = null;
    private String tenanttoken = null;
    private String server = null;

    private String planId = null;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenant() {
        return this.tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public void setServer(String server) { this.server = server; }

    public void setTenanttoken(String tenanttoken) { this.tenanttoken = tenanttoken; }

    public String getTenanttoken() { return tenanttoken; }

    public String getServer() { return server; }

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId() throws Exception {
        if ((this.name == null) || (this.tenant == null) || (this.tenanttoken == null) || (this.server == null)) {
            throw new Exception("No null name,tenant,tenanttoken,server allowed");
        }

        if (this.planId != null)
            return;

        this.planId = UUID.nameUUIDFromBytes((this.name + this.tenant).getBytes()).toString();
    }

    public String toString() {
        return "Plan [name=" + this.name + ", tenant=" + this.tenant + ", tenanttoken=" + this.tenanttoken + ", server=" + this.server +  ", planId=" + this.planId + "]";
    }
}

