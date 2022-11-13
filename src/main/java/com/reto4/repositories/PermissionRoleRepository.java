package com.reto4.repositories;

import com.reto4.models.Permission;
import com.reto4.models.PermissionsRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRoleRepository extends MongoRepository<PermissionsRole, String> {
    Optional<PermissionsRole> getPermissionsRoleByRole__idAndPermission__id(String role__id, String permission__id);
}
