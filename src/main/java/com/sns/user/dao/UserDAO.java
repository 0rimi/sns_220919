package com.sns.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {

	public boolean existloginId(String loginId);
	
	//회원가입
	public int insertUser(User user);
	
	//로그인
	public User seletUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
}
