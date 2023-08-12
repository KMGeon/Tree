package chapter20;

import java.util.List;

public class MemoController {
	
	MemoService service = new MemoService();
	
	public List<MemoVO> getMemos() throws Exception{
		return service.selectList();
	}
	
	public MemoVO getMemo(int searchId) throws Exception{
		return service.selectOne(searchId);
	}
	
	public int insertMemo(MemoVO vo) throws Exception {
		return service.insertMemo(vo);
	}
	
	public int updateMemo(MemoVO vo) throws Exception {
		return service.updateMemo(vo);
	}
	
	public int deleteMemo(int deleteId) throws Exception {
		return service.deleteMemo(deleteId);
	}
}
