package com.llsollu.ezsms.service;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dto.UserDto;

@Service
public interface UserService {

  UserDto createUser(Long id, String name, String userId, String password, String creationDate);

  UserDto getUser(String id);
}

// Long id; // key

// String name;

// String userId;

// String password;

// String creationDate;