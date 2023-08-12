package kr.or.funding.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.funding.util.MyBatisUtil;

public class NoticeDAO {

    private static NoticeDAO instance = new NoticeDAO();
    private final SqlSession sqlSession = MyBatisUtil.getInstance(true);

    public static NoticeDAO getInstance() {
        if (instance == null) {
            instance = new NoticeDAO();
        }
        return instance;
    }

    public int insertNotice(NoticeVO noticeVo) {
        return sqlSession.insert("notice.insertNotice", noticeVo);
    }

    public int deleteNotice(String id) {
        return sqlSession.delete(id);
    }

    public int updateNotice(String id, NoticeVO noticeVO) {
        return sqlSession.update(id, noticeVO);
    }

    public NoticeVO getNotice(String id) {
        return sqlSession.selectOne("notice.getNotice", id);
    }

    public List<NoticeVO> findAll() {
        return sqlSession.selectList("notice.noticeAllList");
    }

}
