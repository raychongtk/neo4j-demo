package com.example.neo4jdemo.apipayload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raychong
 */
public class GetCompanyNetworkResponse {
    @Valid
    public List<Company> companies = new ArrayList<>();

    public enum PartnerRoleView {
        OWNER,
        CONTRIBUTOR,
        VIEWER
    }

    public static class Company {
        @NotBlank
        public String companyId;

        @NotBlank
        public String name;

        @NotBlank
        public String address;

        @NotNull
        public PartnerRoleView partnerRole;
    }
}
