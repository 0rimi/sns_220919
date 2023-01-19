package com.sns.timeline.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.timeline.dao.TimelineDAO;
import com.sns.timeline.model.Post;

@Service
public class TimelineBO {
	
	@Autowired
	private TimelineDAO timelineDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	//포스트 업로드 insert
	public int addPost(int userId, String userLoginId, String content,MultipartFile file) {
		
		//file > imgPath
		String imgPath = null;
		if(file != null) {	// 파일이 있으면,
			imgPath = fileManagerService.saveFile(userLoginId, file);
		}
		
		return timelineDAO.insertPost(userId,content,imgPath);
	}
	
	//포스트리스트
	public List<Post> getPosts(){
		return timelineDAO.selectPosts();
	}
	
}
