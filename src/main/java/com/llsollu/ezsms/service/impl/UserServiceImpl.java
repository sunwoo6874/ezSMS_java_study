package com.llsollu.ezsms.service.impl;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dao.UserDao;
import com.llsollu.ezsms.data.dto.UserDto;
import com.llsollu.ezsms.data.entity.User;
import com.llsollu.ezsms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto createUser(Long id, String name, String userId, String password, String creationDate) {

        User user = new User(id, name, userId, password, creationDate);
        userDao.saveUser(user);

        UserDto UserDto = new UserDto(user.getId(),
                user.getName(), user.getUserId(), user.getPassword(), user.getCreationDate());

        return UserDto;
    }

    @Override
    public UserDto getUser(String id) {
        Long idL = Long.parseLong(id);
        User user = userDao.getUser(idL);
        if (user == null) {
            log.info("@@@@@@@@@@@@@NULL " + idL);

        } else {
            log.info("NOT             @NULL " + idL);

        }
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getUserId(), user.getPassword(),
                user.getCreationDate());
        return userDto;
    }

}