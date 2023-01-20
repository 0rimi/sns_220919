package com.sns.timeline.model;

import com.sns.post.model.Post;
import com.sns.user.model.User;

//View용 객체
public class CardView {
	
	//post
	private Post post;
	
	//comments
	
	
	//likes
	
	//사용자(로그인)가 좋아요를 눌렀는지(boolean)
	
	
	//G&S
	private User user;

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
}
