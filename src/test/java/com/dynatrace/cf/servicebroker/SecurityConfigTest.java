package com.dynatrace.cf.servicebroker;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class SecurityConfigTest extends AbstractControllerTest {

    @Test
    @WithAnonymousUser
    public void requestWithoutCredentialsIsRejected() throws Exception {
        this.mockMvc.perform(get("/v2/catalog").header(API_VERSION, "2.3"))
                .andExpect(status().isUnauthorized());
    }

}
