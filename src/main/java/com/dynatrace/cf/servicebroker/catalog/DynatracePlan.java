package com.dynatrace.cf.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class DynatracePlan {
    // List of credentials that will be redacted before logging them out
    private static final List<String> SECRET_KEYS = Arrays.asList("apitoken", "tenanttoken");

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
        planId = UUID.nameUUIDFromBytes((name + credentials.get("environmentid")).getBytes()).toString();
    }

    @Override
    public String toString() {
        Map<String, Object> printable = new TreeMap<>(credentials);
        for (String secret : SECRET_KEYS) {
            if (printable.containsKey(secret)) {
                printable.put(secret, "REDACTED");
            }
        }
        return "Plan [name=" + name + ", planId=" + planId + ", " + printable + "]";
    }
}
