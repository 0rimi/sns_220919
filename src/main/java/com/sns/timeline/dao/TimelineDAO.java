package com.sns.timeline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.timeline.model.Post;

@Repository
public interface TimelineDAO {
	
	//insert : POST upload
	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imgPath") String imgPath);
	
	//select : POST list
	public List<Post> selectPosts();

}
