package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;

	public void likeToggle(int postId, int userId) {
		// 좋아요 있는지 확인
		if (likeDAO.selectLikeCntByPostIdOrUserId(postId,userId) > 0) {
			// 있으면 제거
			likeDAO.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가
			likeDAO.insertLike(postId, userId);
		}
	}
	
	public boolean existLike(int postId, Integer userId) {
		if(userId == null) { //비로그인
			return false; //무조건 빈하트로 리턴(boolean false = cnt 0)
		}
		return likeDAO.selectLikeCntByPostIdOrUserId(postId,userId) > 0 ? true : false;//로그인
	}
	
	public int getLikeCntByPostId(int postId) {
		return likeDAO.selectLikeCntByPostIdOrUserId(postId, null);
	}
}