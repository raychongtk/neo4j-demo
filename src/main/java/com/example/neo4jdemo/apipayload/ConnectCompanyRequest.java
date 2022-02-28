package com.example.neo4jdemo.apipayload;

import com.example.neo4jdemo.domain.PartnerRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author raychong
 */
public class ConnectCompanyRequest {
    @NotBlank
    public String companyId;

    @NotNull
    public PartnerRole partnerRole;
}
