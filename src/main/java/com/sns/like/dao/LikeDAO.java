package com.sns.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

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
	
	//포스트당 좋아요 리스트
	public List<Like> selectLikeListByPostId(int postId);
	
	//포스트 id별 좋아요 삭제
	public void deleteLikeByPostId(int postId);
}