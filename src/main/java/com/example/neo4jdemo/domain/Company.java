package com.example.neo4jdemo.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author raychong
 */
@Node
public class Company {
    @Id
    @GeneratedValue
    public Long id;

    public String companyId;

    public String name;

    public String address;

    @Relationship("CONNECTED")
    public Set<CompanyNetwork> companyNetworks = new HashSet<>();
}
