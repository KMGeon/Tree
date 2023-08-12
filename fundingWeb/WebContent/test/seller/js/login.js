$(document).ready(function(){
      //비밀번호 조건 만족 불만족 확인하기
      var text_pw = $('#newPassword');
      text_pw.focus(function(){               //포커스 되면
          $('.find_view .pw_chk').css('display', 'block');
      });
      text_pw.blur(function(){              //포커스에서 벗어나면
          
          pw_check();              //pw_check() 함수 실행    
      });


       //비밀번호확인 조건 만족 불만족 확인하기
       var text_pw2 = $('#confirmPassword');
       text_pw2.focus(function(){               //포커스 되면
           $('.find_view .pw2_chk').css('display', 'block');
       });
       text_pw2.blur(function(){              //포커스에서 벗어나면
           
           pw2_check();              //pw2_check() 함수 실행    
       });
   
  


});

function check_input(){ /* 아이디 비밀번호 확인 */
    if (!document.login_form.username.value){
        alert("아이디를 입력하세요");
        document.login_form.username.focus();
        return;
    }
    if (!document.login_form.password.value){
        alert("비밀번호를 입력하세요");
        document.login_form.password.focus();
        return;
    }

    document.login_form.submit();

}

function enterkey(){ /* 엔터 누를 시 로그인 수행 */
    if(window.event.keyCode==13){
        check_input();
    }
}

function show_alert(err){
    if (err==0){
        alert("계정 활성화를 위한 이메일 인증이 필요합니다.");
        return;
    }
    if (err==1){
        alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        return;
    }
    
    
}