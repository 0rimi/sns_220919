package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	
	//insert : POST upload
	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imgPath") String imgPath);
	
	//select : POST list
	public List<Post> selectPosts();

}
