package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private LikeBO likeBO;
	@Autowired
	private FileManagerService fileManagerService;
	
	//포스트 업로드 insert
	public int addPost(int userId, String userLoginId, String content,MultipartFile file) {
		
		//file > imgPath
		String imgPath = null;
		if(file != null) {	// 파일이 있으면,
			imgPath = fileManagerService.saveFile(userLoginId, file);
		}
		
		return postDAO.insertPost(userId,content,imgPath);
	}
	
	//포스트 삭제 delete
	public int deletePostByPostIdUserId(int postId, int userId) {
		//기존 글 가져오기
		Post post = getPostByPostId(postId);
		//이미지 삭제(이미지 필수임)
		fileManagerService.deleteFile(post.getImgPath());
		//댓글들 삭제
		commentBO.deleteCommentByPostId(postId);
		//좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
		//글 삭제
		return postDAO.deletePostByPostIdUserId(postId, userId);
	}
	
	//포스트 리스트 가져오기 select
	public List<Post> getPosts(){
		return postDAO.selectPosts();
	};
	
	//포스트 하나 가져오기
	public Post getPostByPostId(int postId) {
		return postDAO.selectPostByPostId(postId);
	}

}
