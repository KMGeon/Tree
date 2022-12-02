package com.fastcampus.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageHandler {
    private int totalCnt; //총 게시물 수
    private int pageSize; //한 페이지의 크기
    private   int navSize=10; //페이지 내비게이션의 크기
    private int totalPage; //전체 페이지의 수
    private int page; //현재 페이지
    private int beginPage; //내비게이션 첫 페이지
    private int endPage; //내비게이션 마지막 페이지
    private boolean showPrec;  //이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean showNext; //다음

    public PageHandler(int totalCnt, int page) { //총 게시물 수 , 현재 페이지
        this(totalCnt , page,10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize= pageSize;

        totalPage = (int) Math.ceil(totalCnt/(double)pageSize);
        beginPage = page/navSize*navSize+1;//5이면 1 ,
        endPage =Math.min(beginPage+navSize-1,totalPage); //이 둘중에 작은값을 endpage로 사용
        showPrec = beginPage !=1; //1이번 false
        showNext = endPage!=totalPage; //
    }

}
