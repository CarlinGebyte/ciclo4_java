package com.reto4.services;

import com.reto4.Exceptions.BaseCustomException;
import com.reto4.models.Role;
import com.reto4.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAll() {
        return repository.findAll();
    }

    public Optional<Role> getRole(String id) {
        Optional role = repository.findById(id);
        return role;
    }

    public Role create(Role role) {
        repository.findByName(role.getName())
                .ifPresent(e -> {
                    throw new BaseCustomException("El rol ya existe", HttpStatus.BAD_REQUEST.value());
                });
        return repository.save(role);
    }

    public Role update(String id, Role role) {
        Optional<Role> update = repository.findById(id);

        if (!update.isPresent()) return new Role();

        if (role.getName() != null) update.get().setName(role.getName());
        if (role.getDescription() != null) update.get().setDescription(role.getDescription());

        return repository.save(update.get());
    }

    public void delete(String id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent()) repository.deleteById(id);
    }
}
