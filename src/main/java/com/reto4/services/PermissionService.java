package com.reto4.services;

import com.reto4.Exceptions.BaseCustomException;
import com.reto4.models.Permission;
import com.reto4.repositories.PermissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository repository;

    public PermissionService(PermissionRepository repository) {
        this.repository = repository;
    }

    public List<Permission> getAll() {
        return repository.findAll();
    }

    public Optional<Permission> getPermission(String id) {
        Optional<Permission> permission = repository.findById(id);
        return permission;
    }

    public Permission create(Permission permission) {
        repository.findByUrl(permission.getUrl())
                .ifPresent(e -> {
                    throw new BaseCustomException("El permiso ya existe", HttpStatus.BAD_REQUEST.value());
                });
        return repository.save(permission);
    }

    public Permission update(String id, Permission permission) {
        Optional<Permission> update = repository.findById(id);

        if (!update.isPresent()) return new Permission();

        if (permission.getUrl() != null) update.get().setUrl(permission.getUrl());
        if (permission.getMethod() != null) update.get().setMethod(permission.getMethod());

        return repository.save(update.get());
    }

    public void delete(String id) {
        Optional<Permission> permission = repository.findById(id);
        if (permission.isPresent()) repository.deleteById(id);
    }
}
