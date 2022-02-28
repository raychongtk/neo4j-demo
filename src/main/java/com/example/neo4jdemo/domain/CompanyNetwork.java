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
public class CompanyNetwork {
    public String networkId;
    public String ownerCompanyId;
    public String name;
    @Relationship(type = "CONNECTED", direction = Relationship.Direction.INCOMING)
    public Set<CompanyConnection> connections = new HashSet<>();
    @Id
    @GeneratedValue
    private Long id;
}
