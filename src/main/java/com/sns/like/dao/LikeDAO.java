package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
//	public boolean existLike(
//			@Param("postId") int postId, 
//			@Param("userId") int userId);

	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	//public int selectLikeCntByPostId(int postId);
	
	public int selectLikeCntByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);
}