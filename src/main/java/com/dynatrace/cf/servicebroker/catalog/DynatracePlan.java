package com.dynatrace.cf.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynatracePlan {
    private String name = null;
    private String environmentid = null;
    private String tenanttoken = null;
    private String endpoint = null;
    private String apitoken = null;
    private String planId = null;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironmentid() {
        return this.environmentid;
    }

    public void setEnvironmentid(String environmentid) {
        this.environmentid = environmentid;
    }

    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public void setTenanttoken(String tenanttoken) { this.tenanttoken = tenanttoken; }

    public void setApitoken(String apitoken) { this.apitoken = apitoken; }

    public String getTenanttoken() { return tenanttoken; }

    public String getEndpoint() { return endpoint; }

    public String getApitoken() { return apitoken; }

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId() throws Exception {
        System.out.println(this.toString());
        if ((this.name == null) || (this.environmentid == null) || (this.tenanttoken == null && this.apitoken == null)) {
            throw new Exception("No null name,environmentid,apitoken|tenanttoken allowed");
        }

        if (this.planId != null)
            return;

        this.planId = UUID.nameUUIDFromBytes((this.name + this.environmentid).getBytes()).toString();
    }

    public String toString() {
        return "Plan [name=" + this.name + ", environmentid=" + this.environmentid + ", tenanttoken=" + this.tenanttoken + ", apitoken=" + this.apitoken + ", endpoint=" + this.endpoint +  ", planId=" + this.planId + "]";
    }
}

