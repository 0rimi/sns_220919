<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		
		
		<%-- 글쓰기 영역 --%>
		<c:if test="${not empty userId}">
			
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
		</c:if>
		<%--// 글쓰기 영역 끝 --%>
		

		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			
			<c:forEach var="card" items="${cardList}" varStatus="status">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<div>
						<img class="profile" alt="프로필이미지" src="${card.user.profileUrl}">
						<span class="font-weight-bold">${card.user.name}</span>
					</div>
					<%-- 더보기 --%>
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>

				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imgPath}" class="w-100" alt="본문 이미지">
				</div>

				<%-- 좋아요 --%>
				<div class="card-like mx-3 mt-3">
					<%-- 좋아요가 되어있을때 --%>
					<c:if test="${card.filledLike eq true}">
					<a href="#" class="like-btn" data-user-id="${userId}" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="empty heart">
						좋아요 ${card.likeCnt}개
					</a>
					</c:if>
					<%-- 좋아요가 해제 되어있을때 --%>
					<c:if test="${card.filledLike eq false}">
					<a href="#" class="like-btn" data-user-id="${userId}" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
						좋아요 ${card.likeCnt}개
					</a>
					</c:if>
				</div>

				<%-- 글 --%>
				<div class="card-post m-3">
					<div>
						<img class="profile" alt="프로필이미지" src="${card.user.profileUrl}">
						<span class="font-weight-bold">${card.user.name}</span>
					</div>
					<span>${card.post.content}</span>
				</div>

				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>

				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
				
					<c:forEach var="commentView" items="${card.commentViewList}" varStatus="status">
					<div class="card-comment m-1">
						<img class="littleprofile" alt="프로필이미지" src="${commentView.user.profileUrl}">
						<span class="font-weight-bold">${commentView.user.name} : </span>
						<span>${commentView.comment.content}</span>

						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="commentDelBtn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
					</div>
					</c:forEach>
					
					<%-- 댓글 쓰기 --%>
					<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light"
						 data-user-id="${userId}" data-user-name="${userName}" data-post-id="${card.post.id}">게시</button>
					</div>
					</c:if>
				</div>
				<%--// 댓글 목록 끝 --%>
			</div>
			<%--// 카드1 끝 --%>
			</c:forEach>
			
			
		</div>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>

<script>

	$(document).ready(function(){
		
		/////////파일 업로드 이미지 클릭 > file을 동작
		$('#fileUploadBtn').on('click',function(e){
			e.preventDefault();	//a태그의 올라가는 현상 방지(기본태그 현상 방지)
			$('#file').click();	//input file을 클릭한 것과 같은 효과
		});
		
		//////////사용자가 이미지를 선택했을때 : 유효성 확인 및 업로드 된 파일 이름 노출
		$('#file').on('change',function(e){
			//console.log('파일선택');
			let fileName = e.target.files[0].name;
			console.log(fileName);	//e0cf481a966d1769.jpg
			
			//확장자 유효성 확인
			let ext = fileName.split('.').pop().toLowerCase();
			if(ext != 'jpg' && ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'jpeg'){
				alert('이미지 파일만 업로드 할 수 있습니다.');
				$('#file').val("");
				$('#fileName').text('');
				return;
			}
			
			//파일 이름 노출
			$('#fileName').text(fileName);	//태그사이에 값 넣기 : text()
		});
		
		///////포스트 업로드
		// 글 저장 버튼 클릭
		$('#writeBtn').on('click',function(){
			//글내용
			let writeText = $('#writeTextArea').val();
			let file = $('#file').val();
			console.log("업로드 포스트 내용 : "+writeText+file);
			
			//validation
			if(writeText == ''){
				alert('글 내용을 입력해주세요!');
				return;
			}
			if(file == ''){
				alert('파일을 업로드해주세요!');
				return;
			}
			
			
			// 서버 - AJAX
			
			// 이미지를 업로드 할 때는 form태그가 있어야 한다(자바스크립트에서 만듬)
			// append로 넣는 값은 폼태그의 name으로 넣는 것과 같다
			let formData = new FormData();
			formData.append("writeText",writeText);
			formData.append("file",$('#file')[0].files[0]);//여러개를 올리는거면 배열로 ex)file[1]
		
			// ajax 통신으로 formData에 있는 데이터 전송
			$.ajax({
				//request
				type:"POST"
				,url:"/post/create"
				,data:formData	//form객체를 통째로
				,enctype:"multipart/form-data"	//file업로드를 위한 필수설정
				,processData:false	//file업로드를 위한 필수설정
				,contentType:false	//file업로드를 위한 필수설정
				
				//response
				,success:function(data){
					if(data.code==1){
						//성공
						alert("포스트가 업로드 되었습니다");
						location.href="/timeline/timeline_view";
					}else{
						//실패
						alert(data.errorMessage);
					}
				}
				,error:function(e){
					console.log("포스트업로드 실패")
				}
			});
		});
		
	
		///////////댓글쓰기//////////////
		$('.comment-btn').on('click',function(){
			let postId = $(this).data('post-id');
			let userId = $(this).data('user-id');
			let userName = $(this).data('user-name');
			let content = $(this).siblings('input').val();
			
			console.log("포스트번호 : "+postId);
			console.log("로그인사용자 : "+userId+","+userName);
			console.log("글 내용 : "+content);
			
			if(content == ''){
				alert('댓글 내용을 입력해주세요');
				return;
			}
			
			$.ajax({
				//rq
				type:"POST"
				,url:"/comment/create"
				,data:{"postId":postId,"userId":userId,"content":content}
				//rs
				,success:function(data){
					if(data.code == 1){
						location.reload();	
					}else if(data.code == 500){
						alert('로그인을 해주세요.')
						location.href="/user/sign_in_view"
					}
				}
				,error:function(jqXHR, textStatus,errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg+":"+textStatus);
				}				
			});
			
		});		
		/////////댓글쓰기//////////////
		
		/////////////좋아요////////////
		// 좋아요/해제 toggle
		$('.like-btn').on('click', function(e) {
			e.preventDefault();
			
			let userId = $(this).data('user-id');
			//alert(userId);
			if (userId == '') {
				alert("로그인을 해주세요");
				return;
			}
			
			let postId = $(this).data('post-id');
			//alert(postId);
			$.ajax({
				url:"/like/" + postId
				, success:function(data) {
					if (data.code == 1) {
						location.reload(true);
					} else {
						alert(data.errorMessage);	
					}
				}
				, error: function(e) {
					alert("좋아요/해제 하는데 실패했습니다.");
				}
			});
		});
		/////////////좋아요////////////
		
	
	
		
	});

</script>