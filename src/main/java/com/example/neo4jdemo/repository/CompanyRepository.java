package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.domain.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author raychong
 */
@Repository
public interface CompanyRepository extends Neo4jRepository<Company, Long> {
    Optional<Company> findByCompanyId(String companyId);
}
