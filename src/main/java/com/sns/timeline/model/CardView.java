package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.like.model.Like;
import com.sns.post.model.Post;
import com.sns.user.model.User;

//View용 객체
public class CardView {
	
	//post
	private Post post;
	
	//글쓴이
	private User user;
	
	//comments
	private CommentView commentView;
	private List<CommentView> commentViewList;
	
	//사용자(로그인)가 좋아요를 눌렀는지(boolean)
	private boolean filledLike;
	
	//likes
	private int likeCnt;
	private List<Like> likeList;
	
	//G&S
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public CommentView getCommentView() {
		return commentView;
	}

	public void setCommentView(CommentView commentView) {
		this.commentView = commentView;
	}

	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}

	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}
	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public List<Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	

	@Override
	public String toString() {
		return "CardView [post=" + post + ", user=" + user + ", commentView=" + commentView + ", commentViewList="
				+ commentViewList + "]";
	}

	
	
	
	
}
