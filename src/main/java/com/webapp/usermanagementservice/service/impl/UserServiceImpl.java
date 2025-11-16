package com.webapp.usermanagementservice.service.impl;

import com.webapp.usermanagementservice.dto.UserRequest;
import com.webapp.usermanagementservice.dto.UserResponse;
import com.webapp.usermanagementservice.exception.AlreadyExistsException;
import com.webapp.usermanagementservice.exception.UserNotFoundException;
import com.webapp.usermanagementservice.mapper.UserMapper;
import com.webapp.usermanagementservice.model.entity.User;
import com.webapp.usermanagementservice.model.enums.Status;
import com.webapp.usermanagementservice.repository.UserRepository;
import com.webapp.usermanagementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AlreadyExistsException("User already exists with email: "+request.getEmail());
        }

        User user=userMapper.toEntityFromRequest(request);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    //avoid repetition
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User with id "+id+ " not found"));
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user=findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user=findUserById(id);
        userMapper.updateUser(request,user);
        return userMapper.toDto(user);
    }

    //Soft delete
    @Override
    public void deleteUserById(Long id) {
        User user=findUserById(id);
        user.setStatus(Status.DELETED);
    }

    @Override
    public UserResponse updateStatus(Long id, Status status) {
        User user=findUserById(id);
        user.setStatus(status);
        return userMapper.toDto(user);
    }
}
