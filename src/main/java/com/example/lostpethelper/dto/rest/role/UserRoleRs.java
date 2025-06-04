package com.example.lostpethelper.dto.rest.role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRoleRs(
        @NotNull(message = "Role name can`t be empty")
        @Size(min = 3, max = 20, message = "Role should be between 3 and 20")
        String roleName
) { }
