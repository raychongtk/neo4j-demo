package com.example.neo4jdemo.apipayload;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raychong
 */
public class GetCompanyNetworkResponse {
    public List<Company> companies = new ArrayList<>();

    public enum PartnerRoleView {
        OWNER,
        CONTRIBUTOR,
        VIEWER
    }

    public static class Company {
        public String companyId;
        public String name;
        public String address;
        public PartnerRoleView partnerRole;
    }
}
