package com.webapp.usermanagementservice.service.impl;

import com.webapp.usermanagementservice.dto.StatusUpdateRequest;
import com.webapp.usermanagementservice.dto.UserRequest;
import com.webapp.usermanagementservice.dto.UserResponse;
import com.webapp.usermanagementservice.exception.AlreadyExistsException;
import com.webapp.usermanagementservice.exception.UserNotFoundException;
import com.webapp.usermanagementservice.mapper.UserMapper;
import com.webapp.usermanagementservice.model.entity.User;
import com.webapp.usermanagementservice.model.enums.Roles;
import com.webapp.usermanagementservice.model.enums.Status;
import com.webapp.usermanagementservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp(){
        userRequest=new UserRequest(
                "Dilbar",
                "dilbar@mail.ru",
                "994567890123",
                null,
                Roles.ADMIN);

        user = new User();
        user.setId(1L);
        user.setName("Dilbar");
        user.setEmail("dilbar@mail.ru");
        user.setPhone("994567890123");
        user.setStatus(Status.ACTIVE);

        userResponse = new UserResponse(1L,"Dilbar","dilbar@mail.ru","994567890123",null,Roles.ADMIN,Status.ACTIVE);
    }

    @Test
    void createUser_success(){
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userMapper.toEntityFromRequest(userRequest)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponse);

        UserResponse result = userService.createUser(userRequest);
        Assertions.assertNotNull(result);
        assertEquals("Dilbar",result.getName());
    }

    @Test
    void createUser_alreadyExists() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> userService.createUser(userRequest));

        assertEquals("User already exists with email: dilbar@mail.ru", exception.getMessage());
    }

    @Test
    void getAllUsers_success() {
        when(userRepository.findByStatus(Status.ACTIVE)).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponse);

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Dilbar", result.get(0).getName());
    }

    @Test
    void getUserById_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponse);

        UserResponse result = userService.getUserById(1L);

        assertEquals("Dilbar", result.getName());
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void updateUser_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateUser(userRequest, user);
        when(userMapper.toDto(user)).thenReturn(userResponse);

        UserResponse result = userService.updateUser(1L, userRequest);

        assertEquals("Dilbar", result.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUserById_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        assertEquals(Status.DELETED, user.getStatus());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateStatus_success() {
        StatusUpdateRequest statusUpdate = new StatusUpdateRequest(Status.INACTIVE);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponse);

        UserResponse result = userService.updateStatus(1L, statusUpdate);

        assertEquals("Dilbar", result.getName());
        assertEquals(Status.INACTIVE, user.getStatus());
        verify(userRepository, times(1)).save(user);
    }

}
