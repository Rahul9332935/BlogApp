package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.UserNotFoundException;
import com.blog.model.User;
import com.blog.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService, UserService {

	
	
	private UserRepository userRepository;
	
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database
		Optional<User> user =  userRepository.findByUsername(username);
		
		return user.map(UserInfoDetails::new).orElseThrow(()->new UserNotFoundException("no user found "));
	}
	
	public String addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "user added ";
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		User user= userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("no user found with id:- "+id));
		return user;
	}
	
	public void deleteUser(Long id) {
		User user= userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("no user found with id:- "+id));
		userRepository.delete(user);
	}

	

	@Override
	public User updateUser(Long userId, User userDetails) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
