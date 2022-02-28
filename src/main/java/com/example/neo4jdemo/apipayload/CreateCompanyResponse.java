package com.example.neo4jdemo.apipayload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author raychong
 */
public class CreateCompanyResponse {
    @NotNull
    public String id;

    @NotBlank
    public String name;
}
