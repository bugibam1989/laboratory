package com.laboratory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.mapper.UserMapper;
import com.laboratory.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(String user_id) {
		return userMapper.selectUserByUserId(user_id);
	}
}
