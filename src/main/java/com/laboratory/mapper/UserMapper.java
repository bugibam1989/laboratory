package com.laboratory.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.laboratory.model.User;

@Mapper
public interface UserMapper {

	public User selectUserByUserId(String user_id);
}
