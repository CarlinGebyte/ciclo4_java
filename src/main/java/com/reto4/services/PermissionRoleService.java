package com.reto4.services;

import com.reto4.models.Permission;
import com.reto4.models.PermissionsRole;
import com.reto4.models.Role;
import com.reto4.repositories.PermissionRepository;
import com.reto4.repositories.PermissionRoleRepository;
import com.reto4.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionRoleService {
    private final PermissionRoleRepository permissionRoleRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public PermissionRoleService(PermissionRoleRepository permissionRoleRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionsRole> getAll() {
        return permissionRoleRepository.findAll();
    }

    public Optional<PermissionsRole> getPermissionRole(String id) {
        Optional<PermissionsRole> permissionsRole = permissionRoleRepository.findById(id);
        return permissionsRole;
    }

    public PermissionsRole create(String idRole, String idPermission) {
        PermissionsRole permissionsRole = new PermissionsRole();
        Optional<Role> role = roleRepository.findById(idRole);
        Optional<Permission> permission = permissionRepository.findById(idPermission);
        if (!role.isPresent() || !permission.isPresent()) return null;

        permissionsRole.setPermission(permission.get());
        permissionsRole.setRole(role.get());
        return permissionRoleRepository.save(permissionsRole);
    }

    public PermissionsRole update(String id, PermissionsRole permissionsRole) {
        Optional<PermissionsRole> update = permissionRoleRepository.findById(id);

        if (!update.isPresent()) return new PermissionsRole();

        if (permissionsRole.getPermission() != null) update.get().setPermission(permissionsRole.getPermission());
        if (permissionsRole.getRole() != null) update.get().setRole(permissionsRole.getRole());

        return permissionRoleRepository.save(update.get());
    }

    public void delete(String id) {
        Optional<PermissionsRole> exist = permissionRoleRepository.findById(id);
        if (exist.isPresent()) permissionRoleRepository.deleteById(id);
    }
}
