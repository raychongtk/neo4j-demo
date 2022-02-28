package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.domain.CompanyNetwork;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author raychong
 */
@Repository
public interface CompanyNetworkRepository extends Neo4jRepository<CompanyNetwork, Long> {
    Optional<CompanyNetwork> findByOwnerCompanyId(String ownerCompanyId);
}
