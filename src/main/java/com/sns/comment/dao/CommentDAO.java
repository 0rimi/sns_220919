package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	//댓글쓰기
	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	//댓글가져오기
	public List<Comment> selectCommentsByPostId(int postId);
	
}
