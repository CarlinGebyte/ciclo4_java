package com.reto4.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class PermissionsRole {
    @Id
    private String _id;
    private Role role;
    private Permission permission;
}
