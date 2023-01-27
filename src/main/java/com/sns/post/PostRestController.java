package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글 업로드
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String,Object> postCreate(
			@RequestParam("writeText") String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session){
		
		//세션에서 유저 아이디&넘버 받아오기
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		//DB INSERT: 해당 유저 넘버로 저장
		int row = postBO.addPost(userId,userLoginId,content,file);
		
		//result
		Map<String,Object> result = new HashMap<>();

		if(row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		}else {
			result.put("code", 500);
			result.put("errorMessage", "포스트가 업로드 되지 않았습니다.");
		}
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String,Object> deletePost(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		
		//db delete
		int row = postBO.deletePostByPostIdUserId(postId, userId);
		
		//result
		Map<String,Object> result = new HashMap<>();

		if(row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		}else {
			result.put("code", 500);
			result.put("errorMessage", "포스트 삭제에 실패했습니다. 관리자에게 문의하세요.");
		}
		
		return result;
	}

}
