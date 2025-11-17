package com.webapp.usermanagementservice.service;

import com.webapp.usermanagementservice.dto.StatusUpdateRequest;
import com.webapp.usermanagementservice.dto.UserRequest;
import com.webapp.usermanagementservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUserById(Long id);
    UserResponse updateStatus(Long id, StatusUpdateRequest status);
}
