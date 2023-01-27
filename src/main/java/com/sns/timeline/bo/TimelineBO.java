package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {

	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private LikeBO likeBO;

	// 포스트리스트(로그인이 되지 않아도 보여야함)
	public List<CardView> generateCards(Integer userId) {
		List<CardView> cardList = new ArrayList<>();

		// 글목록
		List<Post> postList = postBO.getPosts();
		// postList 반복문 > CardView > CardList에 넣기
//		for(int i=0; i<postList.size(); i++) {
//			cardList.get(i).setPost(postList.get(i));	
//		}
		for (Post post : postList) {
			CardView card = new CardView();

			// 글
			card.setPost(post);

			// 글쓴이
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);

			// 댓글들
			List<CommentView> commentList = commentBO.generateCommentsByPostId(post.getId());
			card.setCommentViewList(commentList);

			// 로그인 유저가 좋아요했는지 여부
			card.setFilledLike(likeBO.existLike(post.getId(), userId));

			// 글에 눌린 좋아요 갯수
			card.setLikeCnt(likeBO.getLikeCntByPostId(post.getId()));

			// card로
			cardList.add(card);
		}

		return cardList;
	}

	/*
	// 포스트 하나 가져오기
	public CardView getCardView(int postId) {
		CardView cardview = new CardView();

		// 포스트하나 가져오기
		cardview.setPost(postBO.getPostByPostId(postId));
		// 댓글들가져오기
		cardview.setCommentViewList(commentBO.generateCommentsByPostId(postId));
		// 좋아요가져오기
		cardview.setLikeList(likeBO.getLikeListByPostId(postId));
		
		return cardview;
	}
	*/

}
