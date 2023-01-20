package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.timeline.model.CardView;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	
	//포스트리스트(로그인이 되지 않아도 보여야함)
	public List<CardView> generateCards(){
		List<CardView> cardList = new ArrayList<>();
		
		//글목록
		
		//postList 반복문 > CardView > CardList에 넣기
		
		return cardList;
	}
	
}
