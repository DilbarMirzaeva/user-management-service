package com.webapp.usermanagementservice.service;

import com.webapp.usermanagementservice.dto.UserRequest;
import com.webapp.usermanagementservice.dto.UserResponse;
import com.webapp.usermanagementservice.model.enums.Status;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUserById(Long id);
    UserResponse updateStatus(Long id, Status status);
}
