package com.example.neo4jdemo.service;

import com.example.neo4jdemo.apipayload.GetMyCompanyNetworkResponse;
import com.example.neo4jdemo.apipayload.PartnerRoleView;
import com.example.neo4jdemo.domain.Company;
import com.example.neo4jdemo.domain.CompanyConnection;
import com.example.neo4jdemo.domain.CompanyNetwork;
import com.example.neo4jdemo.domain.PartnerRole;
import com.example.neo4jdemo.repository.CompanyNetworkRepository;
import com.example.neo4jdemo.util.AuthorizedUser;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author raychong
 */
@Service
public class CompanyNetworkService {
    private final CompanyNetworkRepository companyNetworkRepository;

    public CompanyNetworkService(CompanyNetworkRepository companyNetworkRepository) {
        this.companyNetworkRepository = companyNetworkRepository;
    }

    public void create(Company company, String companyName) {
        var companyNetwork = new CompanyNetwork();
        companyNetwork.networkId = UUID.randomUUID().toString();
        companyNetwork.ownerCompanyId = company.companyId;
        companyNetwork.name = companyName + " Network";
        companyNetworkRepository.save(companyNetwork);

        var companyConnection = new CompanyConnection();
        companyConnection.partnerRole = PartnerRole.OWNER;
        companyConnection.company = company;
        companyNetwork.companyConnections.add(companyConnection);
        companyNetworkRepository.save(companyNetwork);
    }

    public void connect(Company company, PartnerRole partnerRole) {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(AuthorizedUser.CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));

        var companyConnection = new CompanyConnection();
        companyConnection.partnerRole = partnerRole;
        companyConnection.company = company;
        companyNetwork.companyConnections.add(companyConnection);
        companyNetworkRepository.save(companyNetwork);
    }

    public Set<CompanyConnection> get() {
        CompanyNetwork companyNetwork = companyNetworkRepository.findByOwnerCompanyId(AuthorizedUser.CURRENT_COMPANY_ID).orElseThrow(() -> new Error("unknown company network"));
        return companyNetwork.companyConnections;
    }

    public List<GetMyCompanyNetworkResponse.Network> getCompanyNetworkProjection(String companyId) {
        List<PathValue> companyNetworkPaths = companyNetworkRepository.getCompanyNetworkPaths(companyId);
        List<GetMyCompanyNetworkResponse.Network> networks = new ArrayList<>();
        for (PathValue companyNetworkPath : companyNetworkPaths) {
            Path path = companyNetworkPath.asPath();
            Map<String, Object> companyNetworkMap = path.end().asMap();
            var network = new GetMyCompanyNetworkResponse.Network();
            network.companyNetworkName = String.valueOf(companyNetworkMap.get("name"));
            for (Relationship relationship : path.relationships()) {
                if (relationship.endNodeId() == path.end().id()) {
                    Map<String, Object> relationshipMap = relationship.asMap();
                    network.partnerRole = PartnerRoleView.valueOf(String.valueOf(relationshipMap.get("partnerRole")));
                    break;
                }
            }
            networks.add(network);
        }
        return networks;
    }
}
