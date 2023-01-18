<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">

		<c:if test="${not empty userId}">
			<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>

			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		<%--// 글쓰기 영역 끝 --%>
		</c:if>

		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">글쓰니</span>

					<%-- 더보기 --%>
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>

				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="https://cdn.pixabay.com/photo/2022/04/13/20/32/silhouette-7131109_960_720.png" class="w-100" alt="본문 이미지">
				</div>

				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn">
					<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
						좋아요 10개
					</a>
				</div>

				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">글쓰니</span>
					<span>글 내용입니다</span>
				</div>

				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>

				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<div class="card-comment m-1">
						<span class="font-weight-bold">댓글쓰니:</span>
						<span>댓글 내용11111</span>

						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="commentDelBtn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
					</div>

					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
				</div>
				<%--// 댓글 목록 끝 --%>
			</div>
			<%--// 카드1 끝 --%>
		</div>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>

<script>

	$(document).ready(function(){
		
		//파일 업로드 이미지 클릭 > file을 동작
		$('#fileUploadBtn').on('click',function(e){
			e.preventDefault();	//a태그의 올라가는 현상 방지(기본태그 현상 방지)
			$('#file').click();	//input file을 클릭한 것과 같은 효과
		});
		
		//사용자가 이미지를 선택했을때 : 유효성 확인 및 업로드 된 파일 이름 노출
		$('#file').on('change',function(e){
			//console.log('파일선택');
			let fileName = e.target.files[0].name;
			console.log(fileName);	//e0cf481a966d1769.jpg
			
			//확장자 유효성 확인
			let ext = fileName.split('.').pop().toLowerCase();
			if(ext != 'jpg' && ext != 'jpg' && ext != 'gif' && ext != 'png'){
				alert('이미지 파일만 업로드 할 수 있습니다.');
				$('#file').val("");
				$('#fileName').text('');
				return;
			}
			
			//파일 이름 노출
			$('#fileName').text(fileName);	//태그사이에 값 넣기 : text()
		});
		
	});

</script>