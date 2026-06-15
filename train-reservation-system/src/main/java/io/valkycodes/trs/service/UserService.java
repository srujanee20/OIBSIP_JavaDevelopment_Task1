package io.valkycodes.trs.service;

import io.valkycodes.trs.mapper.UserMapper;
import io.valkycodes.trs.model.dto.UserData;
import io.valkycodes.trs.model.po.User;
import io.valkycodes.trs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserData userData) {
        User user = userMapper.toEntity(userData);
        userRepository.save(user);
    }

    public Optional<User> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
