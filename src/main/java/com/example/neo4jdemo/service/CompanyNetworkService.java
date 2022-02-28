package com.example.neo4jdemo.service;

import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;
import com.example.neo4jdemo.domain.CompanyNetwork;
import com.example.neo4jdemo.domain.PartnerRole;
import com.example.neo4jdemo.repository.CompanyNetworkRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author raychong
 */
@Service
public class CompanyNetworkService {
    private final static String CURRENT_COMPANY_ID = "8339172a-c2a5-40ec-8c5f-6fe88577a491";
    private final CompanyNetworkRepository companyNetworkRepository;

    public CompanyNetworkService(CompanyNetworkRepository companyNetworkRepository) {
        this.companyNetworkRepository = companyNetworkRepository;
    }

    public void create(Company company, String companyName) {
        var companyNetwork = new CompanyNetwork();
        companyNetwork.networkId = UUID.randomUUID().toString();
        companyNetwork.ownerCompanyId = company.companyId;
        companyNetwork.name = companyName + " Network";
        companyNetworkRepository.save(companyNetwork);

        var companyConnection = new CompanyConnection();
        companyConnection.partnerRole = PartnerRole.OWNER;
        companyConnection.company = company;
        companyNetwork.companyConnections.add(companyConnection);
        companyNetworkRepository.save(companyNetwork);
    }

    public void connect(Company company, PartnerRole partnerRole) {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));

        var companyConnection = new CompanyConnection();
        companyConnection.partnerRole = partnerRole;
        companyConnection.company = company;
        companyNetwork.companyConnections.add(companyConnection);
        companyNetworkRepository.save(companyNetwork);
    }

    public Set<CompanyConnection> get() {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));
        return companyNetwork.companyConnections;
    }
}
