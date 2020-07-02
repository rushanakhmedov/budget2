package com.budget.budget2.controller;

import com.budget.budget2.entity.User;
import com.budget.budget2.security.jwt.JwtUser;
import com.budget.budget2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users-rest/")
public class UserRestControllerV1 {

    private final Logger logger = LoggerFactory.getLogger(UserRestControllerV1.class);

    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    //@PreAuthorize( "hasRole(@roles.USER)" )
    @GetMapping(value = "{id}")
    public ResponseEntity<String> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole(@roles.USER)")
    @GetMapping(value = "r1/{id}")
    public ResponseEntity<Object> getUserByIdr1(@PathVariable(name = "id") Long id, Principal principal){
        User user = userService.findById(id);
        logger.info("test test");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //JwtUser currentUser = (JwtUser) authentication.getPrincipal();

        authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();

        response.put("username", user.getUsername());

        //response.put("currentUser", currentUser.getId());

        response.put("getDetails", authentication);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            response.put("hasRoleUSER", true);
        }

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
