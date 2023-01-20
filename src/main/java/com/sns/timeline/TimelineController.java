package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
		
		//화면보내기
		model.addAttribute("viewName", "timeline/timeline");
		
		//db select(post,comment)
		List<Post> posts = postBO.getPosts();
		//List<CardView> cardList = timelineBO.generateCards();
		
		//타임라인 목록 내보내기
		model.addAttribute("posts", posts);
		
		return "template/layout";
	}
	
}