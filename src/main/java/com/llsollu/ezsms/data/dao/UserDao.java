package com.llsollu.ezsms.data.dao;

import com.llsollu.ezsms.data.entity.User;

public interface UserDao {

    User saveUser(User user);

    User getUser(Long id);
}
