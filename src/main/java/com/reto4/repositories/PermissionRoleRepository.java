package com.reto4.repositories;

import com.reto4.models.PermissionsRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRoleRepository extends MongoRepository<PermissionsRole, String> {
}
