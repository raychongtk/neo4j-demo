package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.domain.CompanyNetwork;
import org.neo4j.driver.internal.value.PathValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author raychong
 */
@Repository
public interface CompanyNetworkRepository extends Neo4jRepository<CompanyNetwork, Long> {
    Optional<CompanyNetwork> findByOwnerCompanyId(String ownerCompanyId);

    @Query("MATCH p=(company {companyId: $companyId})-[r:CONNECTED]->() RETURN p")
    List<PathValue> getCompanyNetworkPaths(String companyId);
}
