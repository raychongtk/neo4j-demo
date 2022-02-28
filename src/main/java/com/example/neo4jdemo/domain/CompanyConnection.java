package com.example.neo4jdemo.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author raychong
 */
@RelationshipProperties
public class CompanyConnection {
    @RelationshipId
    @GeneratedValue
    public Long id;

    public PartnerRole partnerRole;

    @TargetNode
    public Company company;
}
