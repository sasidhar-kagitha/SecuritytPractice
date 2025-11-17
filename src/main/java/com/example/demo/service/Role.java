package com.example.demo.service;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.DELETE_ACCESS,Permissions.WRITE_ACCESS,Permissions.READ_ACCESS)),
    USER(Set.of(Permissions.READ_ACCESS));

    private Set<Permissions> permissions;

    Role(Set<Permissions> permissions)
    {
        this.permissions=permissions;
    }

    public Set<Permissions> getPermissions()
    {
        return permissions;
    }
}
