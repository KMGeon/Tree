package com.fastcampus.ch4.domain;

import junit.framework.TestCase;
import org.junit.Test;

public class PageHandlerTest extends TestCase {

    @Test
    public void test() {
        PageHandler pageHandler = new PageHandler(250, 1);
        pageHandler.print();
        System.out.println("ph" + pageHandler);
        assertTrue(pageHandler.getBeginPage() == 1);
        assertTrue(pageHandler.getEndPage() == 10);

    }

    @Test
    public void test2() {
        PageHandler ph = new PageHandler(250, 11);
        ph.print();
        System.out.println(ph);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 20);
    }


    @Test
    public void test3() {
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        System.out.println(ph);
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 26);
    }
}