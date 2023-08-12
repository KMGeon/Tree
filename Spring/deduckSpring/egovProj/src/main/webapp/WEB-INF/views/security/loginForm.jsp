<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div style="text-align: center; display: flex; justify-content: center;">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>스프링</b>연습용</a>
		</div>

		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>
				<form action="/login" method="post">
					<div class="input-group mb-3">
						<input type="text" name="username" class="form-control"
							placeholder="아이디" />
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" name="password" class="form-control"
							placeholder="비밀번호" />
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" name="remember-me" id="remember">
								<label for="remember"> Remember Me </label>
							</div>
						</div>

						<div class="col-4">
							<button type="submit" class="btn btn-primary btn-block">Sign
								In</button>
						</div>

					</div>
					<sec:csrfInput />
				</form>
			</div>
		</div>
	</div>
</div>