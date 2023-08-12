function checkAddProduct(){
    //자바스크립트의 신. 철희신. 자신e72.
    let productId = document.getElementById("productId");
    let pname = document.getElementById("pname");
    let unitPrice = document.getElementById("unitPrice");
    let unitsInStock = document.getElementById("unitsInStock");
 
    //상품 아이디 체크. P와 숫자를 조합. 5~12자.
    if(!check(/^P[0-9]{4,11}$/,productId,
    "[상품 코드]\n와 숫자를 조합하여 5~12자까지 입력하세요\n첫 글자는 반드시 P로 시작하세요")){
        return false;
    }
    //상품명 체크. 최소 4자에서 최대 12자
    if(pname.value.length < 4 || pname.value.length > 12){
        alert("[상품명]\n최소 4자에서 최대 12자까지 입력하세요");
        pname.select();
        pname.focus();
        return false;
    }

    //상품 가격 체크.숫자만 입력
    if(unitPrice.value.length == 0 || isNaN(unitPrice.value)){
        alert("[가격]\n숫자만 입력하세요");
        unitPrice.select();
        unitPrice.focus();
        return false;
    }

    //상품 가격 음수 체킹
    if(unitPrice.value < 0){
        alert("[가격]\n음수는 입력할 수 없습니다.");
        unitPrice.select();
        unitPrice.focus();
        return false;
    }else if(!check(/^\d+(?:[.]?[\d]?[\d])?$/,unitPrice,
        "[가격]\n소수점 둘째 자리까지만 입력해주세요")){//소수점 둘째 자리까지만 입력 가능
        return false;
    }

    //제고 수 체크. 숫자만 입력
    if(isNaN(unitsInStock.value)){
        alert("[재고 수]\n숫자만 입력해주세요");
        unitsInStock.select();
        unitsInStock.focus();
        return false;
    }


    //정규식 자동화 함수
    //regExp : 정규식 / e : 대상 객체 / msg : 통과 못하면 출력할 메시지
    function check(regExp, e, msg){
        if(regExp.test(e.value)){//통과하면
            return true;
        }

        //통과하지 못했다면..
        alert(msg);
        e.select();
        e.focus();
        return false;
    }
    //모두 통과했다면 submit
    document.newProduct.submit();
}