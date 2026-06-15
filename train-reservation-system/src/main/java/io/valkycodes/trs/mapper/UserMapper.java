package io.valkycodes.trs.mapper;

import io.valkycodes.trs.model.dto.UserData;
import io.valkycodes.trs.model.po.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserData toDto(User user) {
        UserData userData = new UserData();

        userData.setUsername(user.getUsername());
        userData.setPassword(user.getPassword());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPhoneNo(user.getPhoneNo());

        return userData;
    }

    public User toEntity(UserData userData) {
        User user = new User();

        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPhoneNo(userData.getPhoneNo());

        return user;
    }
}
