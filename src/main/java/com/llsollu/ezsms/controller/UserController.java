package com.llsollu.ezsms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsollu.ezsms.data.dto.UserDto;
import com.llsollu.ezsms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/ezsms/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<UserDto> putUser() {
        UserDto responseDTO = userService.createUser(632L, "홍길동", "hong", "gildong",
                "2023-04-04 16:37:00");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(value = "/select/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        UserDto responseDTO = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
