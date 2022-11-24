package com.reto4.controllers;

import com.reto4.models.Permission;
import com.reto4.models.PermissionsRole;
import com.reto4.models.Role;
import com.reto4.services.PermissionRoleService;
import com.reto4.services.PermissionService;
import com.reto4.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/permission-role/")
public class PermissionsRoleController {
    private final PermissionRoleService service;
    private final PermissionService permissionService;
    private final RoleService roleService;

    public PermissionsRoleController(PermissionRoleService service, PermissionService permissionService, RoleService roleService) {
        this.service = service;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<PermissionsRole> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Optional<PermissionsRole> getPermissionsRole(@PathVariable("id") String id) {
        return service.getPermissionRole(id);
    }

    @GetMapping("/validate-permission/{idRole}")
    public PermissionsRole validatePermission(@PathVariable("idRole") String id, @RequestBody Permission permission) {
        Optional<Permission> permissionRes = permissionService.getByUrlAndMethod(permission.getUrl(), permission.getMethod());
        Optional<Role> role = roleService.getRole(id);


        if ( permissionRes.isPresent() && role.isPresent()) {
            return service.getByRoleIdAndPermissionId(role.get().get_id(), permissionRes.get().get_id()).get();
        }
        return null;
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
