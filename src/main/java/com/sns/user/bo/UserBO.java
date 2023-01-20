package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	//아이디 중복체크
	public boolean existloginId(String loginId) {
		return userDAO.existloginId(loginId);
	}
	
	//회원추가
	public int addUser(User user) {
		return userDAO.insertUser(user);
	}
	
	//로그인
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.seletUserByLoginIdPassword(loginId,password);
	}
	
	//select : 유저아이디로 유저정보 가져오기
	public User getUserById(int userId) {
		return userDAO.seletUserById(userId);
	}

}
