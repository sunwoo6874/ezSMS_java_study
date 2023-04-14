package com.llsollu.ezsms.data.dao.impl;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dao.UserDao;
import com.llsollu.ezsms.data.entity.User;
import com.llsollu.ezsms.data.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    public User getUser(Long id) {
        User user1 = userRepository.findByIdIs(id);
        return user1;
    }
}
