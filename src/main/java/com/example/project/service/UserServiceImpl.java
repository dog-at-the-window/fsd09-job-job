package com.example.project.service;

import com.example.project.dto.UserDto;
import com.example.project.entity.Account;
import com.example.project.entity.User;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Account account = accountRepository.findByName("ROLE_ADMIN");
        if(account == null){
            account = checkRoleExist();
        }
        user.setAccounts(Arrays.asList(account));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Account checkRoleExist(){
        Account account = new Account();
        account.setName("ROLE_ADMIN");
        return accountRepository.save(account);
    }
}
