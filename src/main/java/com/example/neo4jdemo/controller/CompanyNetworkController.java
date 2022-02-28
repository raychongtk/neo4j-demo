package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.apipayload.ConnectCompanyRequest;
import com.example.neo4jdemo.apipayload.GetCompanyNetworkResponse;
import com.example.neo4jdemo.converter.CompanyConverter;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;
import com.example.neo4jdemo.service.CompanyNetworkService;
import com.example.neo4jdemo.service.CompanyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author raychong
 */
@Validated
@RestController
public class CompanyNetworkController {
    private final CompanyService companyService;
    private final CompanyNetworkService companyNetworkService;

    public CompanyNetworkController(CompanyService companyService, CompanyNetworkService companyNetworkService) {
        this.companyService = companyService;
        this.companyNetworkService = companyNetworkService;
    }


    @PostMapping("/api/company-network/connect")
    public void connect(@Valid @RequestBody ConnectCompanyRequest request) {
        Company company = companyService.get(request.companyId);
        companyNetworkService.connect(company, request.partnerRole);
    }

    @Valid
    @GetMapping("/api/company-network")
    public GetCompanyNetworkResponse get() {
        Set<CompanyConnection> companyNetwork = companyNetworkService.get();

        var response = new GetCompanyNetworkResponse();
        response.companies = companyNetwork.stream().map(CompanyConverter::convert).collect(Collectors.toList());
        return response;
    }
}
