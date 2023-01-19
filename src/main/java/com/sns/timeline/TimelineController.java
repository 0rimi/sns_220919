package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.Post;

@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
		
		//화면보내기
		model.addAttribute("viewName", "timeline/timeline");
		
		//db select(post,comment)
		List<Post> posts = timelineBO.getPosts();
		
		//타임라인 목록 내보내기
		model.addAttribute("posts", posts);
		
		return "template/layout";
	}
	
}