package com.example.neo4jdemo.apipayload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raychong
 */
public class GetMyCompanyNetworkResponse {
    @Valid
    public List<Network> networks = new ArrayList<>();

    public static class Network {
        @NotBlank
        public String companyNetworkName;

        @NotNull
        public PartnerRoleView partnerRole;
    }
}
