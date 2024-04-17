package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.User;
import com.blog.service.CustomUserDetailService;
import com.blog.service.JwtService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private CustomUserDetailService detailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to blog Application !!";
    }

    @PostMapping("/register")
    public String addUser(@RequestBody User userInfo){
        return detailService.addUser(userInfo);

    }
    @PostMapping("/login")
    public String addUser(@RequestBody com.blog.model.AuthRequest authRequest){
        System.out.println(authRequest.getUserName()+" "+authRequest.getPassword());
    	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public List<User> getAllUsers(){
        return detailService.getAllUser();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public User getAllUsers(@PathVariable Long id){
        return detailService.getUserById(id);
    }
}

