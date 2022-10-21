package com.example.springsecdemo.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE,
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE
    )),
    ADMIN_TRAINEE(Sets.newHashSet(
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.COURSE_READ
    ));


    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
