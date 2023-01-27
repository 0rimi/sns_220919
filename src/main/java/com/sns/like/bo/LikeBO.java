package com.sns.like.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;
import com.sns.like.model.Like;

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
	
	//로그인 사용자가 하트를 눌렀는지 안눌렀는지
	public boolean existLike(int postId, Integer userId) {
		if(userId == null) { //비로그인
			return false; //무조건 빈하트로 리턴(boolean false = cnt 0)
		}
		return likeDAO.selectLikeCntByPostIdOrUserId(postId,userId) > 0 ? true : false;//로그인
	}
	
	//좋아요 갯수
	public int getLikeCntByPostId(int postId) {
		return likeDAO.selectLikeCntByPostIdOrUserId(postId, null);
	}
	
	//한 포스트에 달린 좋아요 리스트
	public List<Like> getLikeListByPostId(int postId){
		return likeDAO.selectLikeListByPostId(postId);
	}
	
	//포스트 id별 좋아요 삭제
	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostId(postId);
	}
}