package com.reto4.controllers;

import com.reto4.models.Permission;
import com.reto4.services.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/permission/")
public class PermissionController {
    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Permission> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Optional<Permission> getPermission(@PathVariable("id") String id) {
        return service.getPermission(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permission create(@RequestBody Permission permission) {
        return service.create(permission);
    }

    @PutMapping("{id}")
    public Permission update(@PathVariable("id") String id, @RequestBody Permission permission) {
        return service.update(id, permission);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}
