package com.reto4.controllers;

import com.reto4.models.PermissionsRole;
import com.reto4.services.PermissionRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/permission-role/")
public class PermissionsRoleController {
    private final PermissionRoleService service;

    public PermissionsRoleController(PermissionRoleService permissionRoleService) {
        this.service = permissionRoleService;
    }

    @GetMapping
    public List<PermissionsRole> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Optional<PermissionsRole> getPermissionsRole(@PathVariable("id") String id) {
        return service.getPermissionRole(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{idRole}/permission/{idPermission}")
    public PermissionsRole create(@PathVariable("idRole")String idRole, @PathVariable("idPermission") String idPermission) {
        return service.create(idRole, idPermission);
    }

    @PutMapping("{id}")
    public PermissionsRole update(@PathVariable("{id}") String id, @RequestBody PermissionsRole permissionsRole) {
        return service.update(id, permissionsRole);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
