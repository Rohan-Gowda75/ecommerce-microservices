package com.eComm.user.service;

import java.util.List;

import com.eComm.user.dto.UserDto;
import com.eComm.user.request.LoginRequest;
import com.eComm.user.request.RegisterRequest;
import com.eComm.user.request.UpdateRequest;

public interface UserService {

    UserDto register(RegisterRequest request);

    UserDto login(LoginRequest request);

    UserDto getById(Integer userId);

    List<UserDto> getAll();

    void updateUser(Integer userId, UpdateRequest request);

    void deleteById(Integer userId);

}