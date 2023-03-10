package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	
	@PostMapping("/create")
	public Map<String,Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("userId") int userId,
			@RequestParam("content") String content){
		
		// db insert:comment
		int row = commentBO.addComment(postId, userId, content);
		
		Map<String,Object> result = new HashMap<>();
		if(row>0) {
			result.put("code", 1);
			result.put("result", "성공");
		}else{
			result.put("code", 500);
		}
		
		return result;
	}

}
