package kr.or.funding.board.notice;

import java.util.List;

public class NoticeService {

    private static  NoticeService instance = new NoticeService();
    private final NoticeDAO noticeDAO = NoticeDAO.getInstance();

    public static NoticeService getInstance() {
        if (instance == null) {
            instance = new NoticeService();
        }
        return instance;
    }

    public void insertNotice(NoticeVO noticeVo) {
        int numberOfQueryCall = noticeDAO.insertNotice(noticeVo);
        if (numberOfQueryCall != 1) {
            // 에러 메세지는 간지나게 바꾸는걸 추천 ㅎ [예 : "Mybatis query call count isn't 1"]
            throw new IllegalStateException("뭔가 잘못됨");
        }
    }

    public void deleteNotice(String id) {
        int numberOfQueryCall = noticeDAO.deleteNotice(id);
        if (numberOfQueryCall != 1) {
            throw new IllegalStateException("뭔가 잘못됨");
        }
    }

    public void updateNotice(String id, NoticeVO noticeVO) {
        int numberOfQueryCall = noticeDAO.updateNotice(id, noticeVO);
        if (numberOfQueryCall != 1) {
            throw new IllegalStateException("뭔가 잘못됨");
        }
    }

    public NoticeVO getNotice(String id) {
        return noticeDAO.getNotice(id);
    }

    public List<NoticeVO> findAll() {
        return noticeDAO.findAll();
    }


}
