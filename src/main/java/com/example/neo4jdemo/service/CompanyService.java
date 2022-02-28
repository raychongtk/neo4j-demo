package com.example.neo4jdemo.service;

import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;
import com.example.neo4jdemo.domain.CompanyNetwork;
import com.example.neo4jdemo.domain.PartnerRole;
import com.example.neo4jdemo.repository.CompanyNetworkRepository;
import com.example.neo4jdemo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author raychong
 */
@Service
public class CompanyService {
    private final static String CURRENT_COMPANY_ID = "8339172a-c2a5-40ec-8c5f-6fe88577a491";
    private final CompanyRepository companyRepository;
    private final CompanyNetworkRepository companyNetworkRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyNetworkRepository companyNetworkRepository) {
        this.companyRepository = companyRepository;
        this.companyNetworkRepository = companyNetworkRepository;
    }

    public Company create(String name, String address) {
        var company = new Company();
        company.companyId = UUID.randomUUID().toString();
        company.name = name;
        company.address = address;
        companyRepository.save(company);

        var companyNetwork = new CompanyNetwork();
        companyNetwork.networkId = UUID.randomUUID().toString();
        companyNetwork.ownerCompanyId = company.companyId;
        companyNetwork.name = name + " Network";
        companyNetworkRepository.save(companyNetwork);

        var connection = new CompanyConnection();
        connection.partnerRole = PartnerRole.OWNER;
        connection.company = company;
        companyNetwork.connections.add(connection);
        companyNetworkRepository.save(companyNetwork);
        return company;
    }

    public void connect(String companyId, PartnerRole partnerRole) {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));
        Company company = companyRepository.findByCompanyId(companyId).orElseThrow(() -> new Error("unknown company"));

        var connection = new CompanyConnection();
        connection.partnerRole = partnerRole;
        connection.company = company;
        companyNetwork.connections.add(connection);
        companyNetworkRepository.save(companyNetwork);
    }

    public Set<CompanyConnection> getCompanyNetwork() {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));
        return companyNetwork.connections;
    }
}
