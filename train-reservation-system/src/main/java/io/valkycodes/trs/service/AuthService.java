package io.valkycodes.trs.service;

import io.valkycodes.trs.mapper.UserMapper;
import io.valkycodes.trs.model.dto.UserData;
import io.valkycodes.trs.model.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    public void registerUser(UserData userData) {
        userService.registerUser(userData);
    }

    public UserData loginUser(String username, String password) {
        Optional<User> optionalUser = userService.getUserByName(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return userMapper.toDto(user);
            }
        }

        return null;
    }

}
