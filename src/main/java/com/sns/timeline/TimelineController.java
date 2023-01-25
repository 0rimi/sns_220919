package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model,
			HttpSession session) {
		
		//db select(post,comment)
		//List<Post> posts = postBO.getPosts();
		List<CardView> cardList = timelineBO.generateCards((Integer)session.getAttribute("userId"));
		System.out.println(cardList);
		
		//타임라인 목록 내보내기
		model.addAttribute("cardList", cardList);
		
		//화면보내기
		model.addAttribute("viewName", "timeline/timeline");
		
		return "template/layout";
	}
	
}