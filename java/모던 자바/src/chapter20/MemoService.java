package chapter20;

import java.util.List;

public class MemoService {
	private MemoDAO dao = new MemoDAO();
	
	public List<MemoVO> selectList() throws Exception{
		return dao.selectList();
	}
	public MemoVO selectOne(int searchId) throws Exception {
		return dao.selectOne(searchId);
	}
	public int insertMemo(MemoVO vo) throws Exception {
		return dao.insertMemo(vo);
	}
	public int updateMemo(MemoVO vo) throws Exception {
		return dao.updateMemo(vo);
	}
	public int deleteMemo(int deleteId) throws Exception {
		return dao.deleteMemo(deleteId);
	}
	
}
