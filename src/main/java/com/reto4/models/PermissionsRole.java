package com.reto4.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
public class PermissionsRole {
    @Id
    private String _id;
    @DBRef
    private Role role;
    @DBRef
    private Permission permission;
}
