package com.example.neo4jdemo.service;

import com.example.neo4jdemo.apipayload.GetJoinedCompanyNetworkResponse;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author raychong
 */
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyNetworkService companyNetworkService;

    public CompanyService(CompanyRepository companyRepository, CompanyNetworkService companyNetworkService) {
        this.companyRepository = companyRepository;
        this.companyNetworkService = companyNetworkService;
    }

    public Company create(String name, String address) {
        var company = new Company();
        company.companyId = UUID.randomUUID().toString();
        company.name = name;
        company.address = address;
        companyRepository.save(company);

        companyNetworkService.create(company, name);
        return company;
    }

    public Company get(String companyId) {
        return companyRepository.findByCompanyId(companyId).orElseThrow(() -> new Error("unknown company"));
    }

    public List<GetJoinedCompanyNetworkResponse.Network> getJoinedNetworks(String companyId) {
        return companyNetworkService.getJoinedNetworks(companyId);
    }
}
