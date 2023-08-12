<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	$("#aSubmit").on("click",function(){
		$("#frm").submit();
	});
	//결혼유무
	$("#marriaged").on("click",function(){
		let chkYn = $("#marriaged").is(":checked");
		
		if(chkYn){
			$("#marriaged").val("true");
		}else{
			$("#marriaged").val("false");
		}
	});
	
});
</script>
<script type="text/javascript">
$(function(){
	$("#btnZipCode").on("click",function(){
		new daum.Postcode({
			oncomplete:function(data){
				//zipCode
				$("input[name='addressVO.postCode']").val(data.zonecode);	//우편번호5자리
				//addressName
				$("input[name='addressVO.address']").val(data.address);	//주소
				//addressDetail
				$("input[name='addressVO.addressDetail']").val(data.buildingName);	//상세 주소
			}
		}).open();
	});
});
</script>
<div class="card shadow mb-4">
    <!-- Card Header - Accordion -->
    <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
        <h6 class="m-0 font-weight-bold text-primary">Collapsable Card Example</h6>
    </a>
    <!-- Card Content - Collapse -->
    <div class="collapse show" id="collapseCardExample" style="">
        <div class="card-body">
        <!-- --------------- cart-body 시작 ------------------- -->
        <form id="frm" action="/register/register05" method="post">
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">userId</label>
			  <input type="text" class="form-control" name="userId" id="userId" 
			  		placeholder="userId" value="a001" required />
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">password</label>
			  <input type="password" class="form-control" name="password" id="password" 
			  		placeholder="password" value="java" required />
<!-- 			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea> -->
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">coin</label>
			  <input type="text" class="form-control" name="coin" id="coin" 
			  		placeholder="coin" value="100" required />
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">birth</label>
			  <input type="date" class="form-control" name="birth" id="birth" 
			  		placeholder="birth" value="2022-11-01" required />
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">gender</label>
			  	<p>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" id="gender1" value="male">
						<label class="form-check-label" for="gender1">Male</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" id="gender2" value="female"
							checked>
						<label class="form-check-label" for="gender2">Female</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" id="gender3" value="other">
						<label class="form-check-label" for="gender3">Other</label>
					</div>
				</p>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">nationality</label>
			  <p>
			  	<select name="nationality" class="form-select" aria-label="Default select example">
				  <option selected>Select your nation</option>
				  <option value="korea" selected>대한민국</option>
				  <option value="america">미국</option>
				  <option value="australia">호주</option>
				</select>
			  </p>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">cars</label>
			  <p>
			  	<select name="cars" class="form-select" multiple aria-label="multiple select example">
				  <option selected>Select your cars</option>
				  <option value="k5">K5</option>
				  <option value="qm6">qm6</option>
				  <option value="audi">audi</option>
				</select>
			  </p>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">hobbys</label>
			  <p>
			  	<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="hobbyList" id="hobbyList1" value="footsal">
				  <label class="form-check-label" for="hobbyList1">footsal</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="hobbyList" id="hobbyList2" value="movie">
				  <label class="form-check-label" for="hobbyList2">movie</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="hobbyList" id="hobbyList3" value="drama">
				  <label class="form-check-label" for="hobbyList3">drama</label>
				</div>
			  </p>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">marriaged</label>
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <input class="form-check-input" type="checkbox" name="marriaged" id="marriaged" value="false" aria-label="...">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">address</label>
			  <div class="p-5">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                        <!-- 골뱅이ModelAttribute MemberVO memberVO -->
                            <input type="text" class="form-control form-control-user" 
                            name="addressVO.postCode" value="63309" id="postCode" placeholder="postCode" />                            
                        </div>
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <a href="#" id="btnZipCode" class="btn btn-info btn-icon-split">
                                <span class="icon text-white-50">
                                    <i class="fas fa-info-circle"></i>
                                </span>
                                <span class="text">Search For PostCode</span>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control form-control-user" 
                        name="addressVO.address" value="제주특별자치도 제주시 첨단로 242" id="address" placeholder="address" />
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control form-control-user" 
                        name="addressVO.addressDetail" value="123" id="addressDetail" placeholder="addressDetail" />
                    </div>
              </div>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Cards</label>
			  <div class="col-lg-6">
                    <div class="card mb-4 py-3 border-left-primary">
                        <div class="card-body">
			                            번호 : <input type="text" class="form-control form-control-user" 
                        			name="cardVOList[0].no" id="no1" placeholder="no" />
			                            유효년월 : <input type="date" class="form-control form-control-user" 
                        			name="cardVOList[0].validMonth" id="validMonth1" placeholder="validMonth" />
                        </div>
                    </div>
                    <div class="card mb-4 py-3 border-left-primary">
                        <div class="card-body">
			                            번호 : <input type="text" class="form-control form-control-user" 
                        			name="cardVOList[1].no" id="no1" placeholder="no" />
			                            유효년월 : <input type="date" class="form-control form-control-user" 
                        			name="cardVOList[1].validMonth" id="validMonth1" placeholder="validMonth" />
                        </div>
                    </div>
              </div>
			</div>
			<div class="mb-3">
			  <a href="#" id="aSubmit" class="btn btn-primary btn-icon-split">
                 <span class="icon text-white-50">
                     <i class="fas fa-flag"></i>
                 </span>
                 <span class="text">요청 파라미터Go</span>
             </a>
			</div>
		</form>
		<!-- --------------- cart-body 끝 ------------------- -->
        </div>
    </div>
</div>


