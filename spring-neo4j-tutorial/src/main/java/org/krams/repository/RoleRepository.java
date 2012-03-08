package org.krams.repository;

import org.krams.domain.Role;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface RoleRepository extends GraphRepository<Role>  {
	
}
