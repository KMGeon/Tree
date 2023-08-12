
    var v_disp = document.querySelector("#id_disp");
    var v_dataArr = JSON.parse(localStorage.getItem(v_tblName));
    /*  페이지를 나누려면 산수가 필요함
        페이지당 글 수?
        페이지번호 ?
        전체 글 수 ? 
    */
    var v_pDsu = 10;   // 페이지당 10개가 나오게 하려고 함
    var v_pageNum = request.getParameter("pageNum");
    if(!v_pageNum){
        v_pageNum =1; // 페이지넘버가 넘어 온 저기 업다문 기본 1페이징
    }
       
    var v_totalG = v_dataArr.length; 
    var v_pageGasu = Math.ceil(v_totalG / v_pDsu); //나머지가 있으면 1페이지 더
    // 페이지별 글 시작넘버, 끝넘버가 필요
    // 1페이지는 0~9 인덱스, 2 페이지는 10~19...
    var v_sNum = (v_pageNum -1) * v_pDsu ;  // 페이지별 글 시작넘버(곧 배열인덱스 넘버)
    var v_eNum = v_sNum + (v_pDsu -1) ;  // 페이지별 글 끝 넘머, 시작넘버+ 페이지별갯수-1
    
    //마지막 페이지는 글 갯수가 모자랄 수 있음, 뽀인토
    if(v_eNum > (v_totalG -1)){
        v_eNum = v_totalG -1;  // 강제로 마지막 index 갑쓰로 쎄팅
    }

    var v_tblStr = "<table border=2 width=600>";
    v_tblStr += "<tr><th>순번</th><th>제목</th><th>글쓰니</th>";
    v_tblStr += "<th>카테고링</th><th>레베르</th><th>싹쩨</th></tr>";    
    for(var i=v_sNum; i<= v_eNum; i++){
        var v_ggul = v_dataArr[i];
        console.log("i=",i," v_ggul=",v_ggul); // 누느로 항쌍 화긴
        v_tblStr += "<tr onmouseover='f_over(this)' onmouseout='f_out(this)' >";
        v_tblStr += "<td>" + (i+1) + "</td>";    
        v_tblStr += "<td><a href=read.html?gnum=" + v_ggul.pid + " >" + v_ggul.title + "</a></td>";    
        v_tblStr += "<td>" + v_ggul.writer + "</td>";    
        v_tblStr += "<td>" + v_ggul.cats + "</td>";    
        v_tblStr += "<td>" + v_ggul.level + "</td>";
        v_tblStr += "<td><input type=checkbox name=nm_del value=" + v_ggul.pid + " ></td>";    
        v_tblStr += "</tr>";    
    }
    v_tblStr += "</table><br><br>";
    
    //페이지 넘버 화면에 출력해 보깅
    // 뜬금없는 문제 11페이지(결국 마지막페이지 처리) 안 나오는 이유를 찾으시옹
    // 힌토 11페이지 글은 10개가 안됨???
    for(var i=1; i <= v_pageGasu; i++ ){
        if(i == v_pageNum){
            v_tblStr += "<a class=active href='list.html?pageNum="+ i +"' >" + i + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }else {
            v_tblStr += "<a href='list.html?pageNum="+ i +"' >" + i + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }
    }
    v_tblStr += "<hr><a href=write.html>쌔에끌쓰깅</a><br>";
    // 버튼에 기능 구현을 해봅니다.(여러개 삭젱)
    // 배열에서 삭제는 splice(인덱스,갯수)메소드를 사용함당
    v_tblStr += "<input id=id_del type=button value='선택된 것만 삭제하깅'>";
    v_disp.innerHTML = v_tblStr;
    //순서(Sequence)에 의해 프로그램이 굉장히 영향을 마니 바더용!!

    var v_delBtn = document.querySelector("#id_del");
    var v_delBoxs = document.querySelectorAll("input[name=nm_del]");
  
    function f_del(){
        // 체크된 체크박스의 value값 가져오깅
        var v_checkedVals = [];  // 빈 배열
        for(var i=0; i < v_delBoxs.length; i++){
            if(v_delBoxs[i].checked){
                v_checkedVals.push(v_delBoxs[i].value);
            }
        }
       // alert(v_checkedVals); // 결과 누느로 화긴!
       // 해당하는 값 배열에서 지우깅
       for(var i=0; i< v_checkedVals.length; i++){
            for(var j=0; j<v_dataArr.length; j++){
                if(v_dataArr[j].pid == v_checkedVals[i]){
                    v_dataArr.splice(j,1); // 1개 지우깅
                    break;
                }
            }
       }
       console.log(v_dataArr);// 누느로 화긴 
       //v_dataArr는 메모리상의 변수라서, 실제 localStorage에서는
       //안 지워져 있으므로,localStorage에 덮어쓰기로 저장
       localStorage.setItem(v_tblName, JSON.stringify(v_dataArr));
       
       //화면이 안 고쳐져 있어서, 화면을 새로 불러와야 함(아님 새로 만들든가)
       location.replace("list.html?pageNum="+v_pageNum); // 다시 불러오깅

    }
    v_delBtn.addEventListener("click",f_del);
