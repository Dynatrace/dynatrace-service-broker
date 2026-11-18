package com.dynatrace.cf.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DynatracePlan {
    private String name;
    private final Map<String, Object> credentials = new HashMap<>();
    private String planId;

    public void setName(String name) { this.name = name; }

    @JsonAnySetter
    public void setCredential(String key, Object value) { credentials.put(key, value); }

    public String getName() { return name; }

    public Map<String, Object> getCredentials() { return credentials; }

    public String getPlanId() { return planId; }

    public void validate() throws Exception {
        if (name == null || credentials.get("environmentid") == null || credentials.get("apitoken") == null) {
            throw new Exception("No null name, environmentid,apitoken allowed");
        }
        Object environmentid = credentials.getOrDefault("environmentid", "");
        planId = UUID.nameUUIDFromBytes((name + environmentid).getBytes()).toString();
    }

    @Override
    public String toString() {
        return "Plan [name=" + name + ", planId=" + planId + "]";
    }
}
