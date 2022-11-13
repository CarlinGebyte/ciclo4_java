package com.reto4.repositories;

import com.reto4.models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findPermissionByUrl(String url);
    Optional<Permission> findPermissionByUrlAndMethod(String url, String method);
}
