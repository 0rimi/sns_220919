<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div id="joinBox" class="mt-3 mb-5">
		<h3>회원가입</h3>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<div class="box-shadow">
				<div>
					<label for="loginId" class="d-block">ID</label>
					<div class="d-flex align-items-center">
						<input name="loginId" class="form-control col-8" type="text" id="loginId"
							placeholder="ID를 입력해주세요">
						<button id="loginIdCheckBtn" type="button" class="btn btn-primary align-items-center ml-2">중복확인</button>
					</div>
					
					<%-- 아이디 체크 결과 --%>
					<%-- d-none 클래스: display none (보이지 않게) --%>
					<div id="idDnone">
						<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">사용중인 아이디입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
					</div>
				</div>
				<div>
					<label for="password" class="d-block">password</label> <input
						name="password" class="form-control col-6" type="password" id="password">
				</div>
				<div>
					<label for="confirmPassword" class="d-block">confirm
						password</label> <input name="confirmPassword" class="form-control col-6" type="password"
						id="confirmPassword">
				</div>
				<div>
					<label for="name" class="d-block">이름</label> <input
						class="form-control col-8" type="text" id="name"
						name="name" placeholder="이름을 입력해주세요">
				</div>
				<div>
					<label for="email" class="d-block">이메일</label> <input
						class="form-control col-8" type="text" id="email" name="email" 
						placeholder="이메일을 입력해주세요">
				</div>
	
				<div class="d-flex justify-content-center w-100">
					<button type="submit" id="signUpBtn" class="btn btn-primary mt-3">가입하기</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!---------- script -------------->
<script>

	$(document).ready(function(){
		
		// 중복확인 버튼 클릭
		$('#loginIdCheckBtn').on('click',function(){
			
			console.log('중복확인');
			// 초기화
			$('#idCheckLength').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			
			let loginId = $('input[name=loginId]').val().trim();
			
			if(loginId.length<4){
				$('#idCheckLength').removeClass('d-none');
				return;
			}
			
			// AJAX
			$.ajax({
				
				//rq
				//type:"GET"	GET은 생략가능
				url:"/user/is_duplicated_id"
				,data:{"loginId":loginId}
				//rs
				,success:function(data){
					if(data.code == 1){	//성공
						if(data.result){	//true,중복
							console.log("중복");
							$('#idCheckDuplicated').removeClass('d-none');
						}else{
							console.log('사용가능');
							$('#idCheckOk').removeClass('d-none');
						}
					}else{
						alert(data.errorMessage);
					}
				}
				,error:function(e){
					alert('중복확인에 실패했습니다.');
				}
			});
			
		});
		
		//회원가입
		$('#signUpForm').on('submit',function(e){
			e.preventDefault(); //서브밋 기능 중단
			
			// validation
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			let confirmPassword = $('#confirmPassword').val().trim();
			let name = $('#name').val().trim();
			let email = $('#email').val().trim();
			
			if(loginId == ''){
				alert('아이디를 입력해주세요');
				return false;	//submit이라
			}
			if(password == '' || confirmPassword == ''){
				alert('비밀번호를 확인해주세요');
				return false;
			}
			if(password != confirmPassword){
				alert('비밀번호가 일치하지 않습니다');
				return false;
			}
			if(name == ''){
				alert('이름을 입력해주세요');
				return false;
			}
			if(email == ''){
				alert('이메일을 입력해주세요');
				return false;
			}
			
			// 아이디 중복체크 확인 했는지 확인 > idCheckOk d-none 없어야함
			if($('#idCheckOk').hasClass('d-none')){	//true 면 가지고있음
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}
		
///////////////////사용가능 뜬상태로 글자수 줄이면 넘어가는 오류 발생 어떻게 해결해볼지 생각
			
			
			//서버로 보내는 방법
			// 1) submit 작동
			//$(this)[0].submit();	//this(form)태그의 첫번째
			
			// 2) ajax
			let url = $(this).attr('action');
			let params = $(this).serialize();//form태그의 name으로 파라미터들 구성
			console.log(params);
			
			// ajax의 한종류
			$.post(url, params)
			.done(function(data){
				//response
				if(data.code==1){
					// 성공
					alert("가입을 환영합니다! 로그인 해주세요.")
					location.href="/user/sign_in_view"
				}else{
					// 실패
				}
				
			});
		});
		
	});
	
</script>