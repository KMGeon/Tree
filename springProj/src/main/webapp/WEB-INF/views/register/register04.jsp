<script type="text/javascript" src="/resources/js/jquery.min.js">
</script>
<script type="text/javascript">
$(function(){
	$("#aSubmit").on("click",function(){
		$("#frm").submit();
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
	<div class="collapse show" id="collapseCardExample" style="">
		<div class="card-body">
		<form action="/register/register04" id="frm" method="post">
			<div class="mb-3">
				<input type="text" class="form-control" id="userId" name="userId"
					placeholder="userId" required="required">
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" id="password" name="password"
					placeholder="password" required="required">
			</div>
			<div class="mb-3">
				
				<input type="text" class="form-control" id="coin"  name="coin"
					placeholder="coin" required="required">  
					<a href="#"class="btn btn-primary btn-icon-split" id="aSubmit">
					 <span class="icon text-white-50"> <i class="fas fa-flag"></i></span> 
					 <span class="text">button</span>
				</a>
			</div>
			</form>
		</div>
	</div>
</div>
<!-- 	<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3"></textarea> -->