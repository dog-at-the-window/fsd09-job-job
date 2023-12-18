package com.example.project.service;

import com.example.project.entity.User;
import com.example.project.dto.UserDto;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
