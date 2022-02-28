package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.apipayload.ConnectCompanyRequest;
import com.example.neo4jdemo.apipayload.CreateCompanyRequest;
import com.example.neo4jdemo.apipayload.CreateCompanyResponse;
import com.example.neo4jdemo.apipayload.GetCompanyNetworkResponse;
import com.example.neo4jdemo.converter.CompanyConverter;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;
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
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Valid
    @PostMapping("/api/company")
    public CreateCompanyResponse create(@Valid @RequestBody CreateCompanyRequest request) {
        Company company = companyService.create(request.name, request.address);

        var response = new CreateCompanyResponse();
        response.id = company.companyId;
        response.name = company.name;
        return response;
    }

    @PostMapping("/api/company/connect")
    public void connect(@Valid @RequestBody ConnectCompanyRequest request) {
        companyService.connect(request.companyId, request.partnerRole);
    }

    @GetMapping("/api/company/network")
    public GetCompanyNetworkResponse getCompanyNetwork() {
        Set<CompanyConnection> companyNetwork = companyService.getCompanyNetwork();
        var response = new GetCompanyNetworkResponse();
        response.companies = companyNetwork.stream().map(CompanyConverter::convert).collect(Collectors.toList());
        return response;
    }
}
