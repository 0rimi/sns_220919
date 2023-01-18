<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div id="loginBox" class="mt-3 mb-5">
		<h3>로그인</h3>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="box-shadow">
				<div class="input-group mb-3">
					<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
					<div class="input-group-prepend">
						<span class="input-group-text">ID</span>
					</div>
					<input type="text" class="form-control" id="loginId" name="loginId">
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">PW</span>
					</div>
					<input type="password" class="form-control" id="password"
						name="password">
				</div>

				<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
				<input type="submit" class="btn btn-block btn-primary" value="로그인">
				<a class="btn btn-block btn-dark" href="/user/sign_up_view">회원가입</a>

			</div>
		</form>
	</div>
</div>


<script>
	$(document).ready(function() {

		$('#loginForm').on('submit', function(e) {
			//서브밋 기능 중단
			e.preventDefault();

			//validation
			let loginId = $('input[name=loginId]').val().trim();
			let password = $('#password').val().trim();

			if (loginId == '') {
				alert("아이디를 입력해주세요");
				return false;
			}
			if (password == '') {
				alert("비밀번호를 입력해주세요")
				return false;
			}

			//ajax
			let url = $(this).attr('action');
			let params = $(this).serialize();
			console.log(params);

			$.post(url, params) //request
			.done(function(data) { //response
				if (data.code == 1) { //성공
					location.href = "/timeline/timeline_view";
				} else { //실패
					alert(data.errorMessage);

				}
			});

		});

	});
</script>