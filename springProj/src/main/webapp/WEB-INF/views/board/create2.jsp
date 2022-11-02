<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	$(function() {
		$('#aSubmit').on('click', function() {
			$('#frm').submit();
		});
	});
</script>


<div class="card shadow mb-4">
	<!-- Card Header - Accordion -->
	<a href="#collapseCardExample" class="d-block card-header py-3"
		data-toggle="collapse" role="button" aria-expanded="true"
		aria-controls="collapseCardExample">
		<h6 class="m-0 font-weight-bold text-primary">Collapsable Card
			Example</h6>
	</a>
	<!-- Card Content - Collapse -->
	<div class="collapse show" id="collapseCardExample">
		<div class="card-body">
			<!-- ================card body 시작================= -->
			<form id="frm" action="/create2" method="post">
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memId</label>
					<input type="text" class="form-control" name="memId" id="memId"
						placeholder="memId" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlTextarea1" class="form-label">memName</label>
					<input type="text" class="form-control" name="memName" id="memName"
						placeholder="memName" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memJob</label>
					<input type="text" class="form-control" name="memJob" id="memJob"
						placeholder="memJob" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memLike</label>
					<input type="text" class="form-control" name="memLike" id="memLike"
						placeholder="memLike" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">memHp</label>
					<input type="text" class="form-control" name="memHp" id="memHp"
						placeholder="memHp" required />
				</div>
				<div class="mb-3">
					<a id="aSubmit" class="btn btn-primary btn-icon-split"> <span
						class="icon text-white-50"> <i class="fas fa-flag"></i>
					</span> <span class="text">등록</span>
					</a>
				</div>
			</form>
		</div>
	</div>
</div>