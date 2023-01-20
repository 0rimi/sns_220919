package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
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
	
	//포스트 리스트 가져오기 select
	public List<Post> getPosts(){
		return postDAO.selectPosts();
	};

}
