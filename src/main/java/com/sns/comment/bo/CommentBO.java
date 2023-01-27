package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private UserBO userBO;
	
	//댓글쓰기
	public int addComment(int postId, int userId, String content) {
		return commentDAO.insertComment(postId,userId,content);
	}
	
	
	//포스트id별 코멘트 가져오기 : select
	private List<Comment> getCommentsByPostId(int postId){
		return commentDAO.selectCommentsByPostId(postId);
	}
	
	//가져온 코멘트리스트에 사용자 정보 추가 commentView
	public List<CommentView> generateCommentsByPostId(int postId){
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		List<Comment> commentList = getCommentsByPostId(postId);
		//c>cv
		for(Comment comment:commentList) {
			CommentView commentView = new CommentView();
			
			//cv에 comment정보 추가
			commentView.setComment(comment);
			
			//cv에 user정보추가
			//comment의 유저 아이디로 유저정보가져오기
			commentView.setUser(userBO.getUserById(comment.getUserId()));
			
			//cvlist에 cv넣어주기
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
	//포스트 id별 코멘트 삭제
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}
	
}
