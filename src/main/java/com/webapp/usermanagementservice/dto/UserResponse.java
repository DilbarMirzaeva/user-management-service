package com.webapp.usermanagementservice.dto;

import com.webapp.usermanagementservice.model.enums.Roles;
import com.webapp.usermanagementservice.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Roles role;
    private Status status;
}
