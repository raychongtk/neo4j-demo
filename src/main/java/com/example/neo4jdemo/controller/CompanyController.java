package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.apipayload.CreateCompanyRequest;
import com.example.neo4jdemo.apipayload.CreateCompanyResponse;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.service.CompanyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
