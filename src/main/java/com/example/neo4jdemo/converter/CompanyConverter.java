package com.example.neo4jdemo.converter;

import com.example.neo4jdemo.apipayload.GetCompanyNetworkResponse;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;

import static com.example.neo4jdemo.apipayload.GetCompanyNetworkResponse.PartnerRoleView;

/**
 * @author raychong
 */
public class CompanyConverter {
    public static GetCompanyNetworkResponse.Company convert(CompanyConnection companyConnection) {
        var companyView = new GetCompanyNetworkResponse.Company();
        Company company = companyConnection.company;
        companyView.companyId = company.companyId;
        companyView.name = company.name;
        companyView.address = company.address;
        companyView.partnerRole = PartnerRoleView.valueOf(companyConnection.partnerRole.name());
        return companyView;
    }
}
