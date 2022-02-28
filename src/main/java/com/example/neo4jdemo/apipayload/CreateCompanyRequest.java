package com.example.neo4jdemo.apipayload;

import javax.validation.constraints.NotBlank;

/**
 * @author raychong
 */
public class CreateCompanyRequest {
    @NotBlank
    public String name;

    @NotBlank
    public String address;
}
