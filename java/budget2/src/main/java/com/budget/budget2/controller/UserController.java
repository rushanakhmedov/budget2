package com.budget.budget2.controller;

import com.budget.budget2.entity.User;
import com.budget.budget2.mapper.UserMapper;
import com.budget.budget2.response.Response;
import com.budget.budget2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    @PreAuthorize("hasRole(@roles.ADMIN)")
    public Response<?> getAll() {
        Response<Object> response = new Response<>();
        List<User> userList = userService.getAll();

        response.setContent(userMapper.fromUserListToUserDTOList(userList));

        return response;
    }


}
