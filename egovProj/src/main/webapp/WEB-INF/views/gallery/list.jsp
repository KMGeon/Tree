<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 목록</title>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="col-12">
		<div class="card card-primary">
			<div class="card-header">
				<div class="form-group" data-select2-id="29">
					<label>Minimal</label> <select
						class="form-control select2 select2-hidden-accessible"
						style="width: 100%;" data-select2-id="1" tabindex="-1"
						aria-hidden="true">
						<div class="form-group">
							<label>Custom Select</label>
							<!-- ajax를 통해 appenc -->
							<select class="custom-select">
								<option value="1">option 1</option>
								<option value="2">option 2</option>
								<option value="3">option 3</option>
								<option value="4">option 4</option>
								<option value="5">option 5</option>
							</select>
						</div>
					</select><span
						class="select2 select2-container select2-container--default select2-container--below select2-container--focus"
						dir="ltr" data-select2-id="2" style="width: 100%;"><span
						class="selection"><span
							class="select2-selection select2-selection--single"
							role="combobox" aria-haspopup="true" aria-expanded="false"
							tabindex="0" aria-disabled="false"
							aria-labelledby="select2-tf9p-container"><span
								class="select2-selection__rendered" id="select2-tf9p-container"
								role="textbox" aria-readonly="true" title="Alaska">Alaska</span><span
								class="select2-selection__arrow" role="presentation"><b
									role="presentation"></b></span></span></span><span class="dropdown-wrapper"
						aria-hidden="true"></span></span>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<c:forEach var="vo" items="${bookVO.attachVOList}">
						<div class="col-sm-2">
							<!-- bookVO에 맴버변수 ATTACHVOLIST에 접근을 해야된다. ->LIST<ATTACHVO> -->
							<a class="btn btn-modal" data-toggle="modal"
								href="#modal-default" data-id="/resources/upload/${vo.filename}">
								<img src="/resources/upload/${vo.filename}" id=""
								class="img-fluid mb-2" alt="white sample">
							</a>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-default" style="display: none;"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title">타이틀</h4>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="body-content"></p>
				</div>
				<div class="modal-footer justify-content-between">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Default Modal 끝 -->
	<script type="text/javascript">
		$(function() {
			// data-id=".........."
			$(".btn-modal").click(
					function() {
						let data = $(this).data("id");
						let title = $(this).data("title");
						console.log("data : " + data, "title" + title);

						$("#body-content")
								.html(
										"<img src='" + data
												+ "' style='width:100%;'/>");
						$(".modal-title").text(title);
					});
			$.ajax({
				url : "/gallery/boardList",
				datatype : "json",
				type : "get",
				success : function(data) {
					//data:List<BookVO>
					console.log("data:" + JSON.stringify(data));
					//item : BOOKVO
					$.each(data, function(index, ite) {
						console.log("item" + item.bookId);
						console.log("item" + item.title);
					});
				}
			});

		});
	</script>
</body>
</html>