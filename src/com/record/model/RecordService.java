package com.record.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordService {
	private RecordDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public RecordService() {
		dao = new RecordJNDIDAO();
	}

	public RecordVO addRecord(java.sql.Date rcd_uploadtime, String rcd_content, String path_no, String mb_id) {

		RecordVO recordVO = new RecordVO();

		recordVO.setRcd_uploadtime(rcd_uploadtime);
		recordVO.setRcd_content(rcd_content);
		recordVO.setPath_no(path_no);
		recordVO.setMb_id(mb_id);
//System.out.println("here");
//System.out.println(recordVO.getMb_id());
		dao.insert(recordVO);
		
		return recordVO;
	}

	public RecordVO updateRecord(String rcd_no, java.sql.Date rcd_uploadtime, String rcd_content, Integer rcd_status, String path_no) {

		RecordVO recordVO = new RecordVO();
		
		recordVO.setRcd_no(rcd_no);
		recordVO.setRcd_uploadtime(rcd_uploadtime);
		recordVO.setRcd_content(rcd_content);
		recordVO.setRcd_status(rcd_status);
		recordVO.setPath_no(path_no);
		dao.update(recordVO);
//		recordVO.setRcd_thumb_amount(rcd_thumb_amount);
//		recordVO.setRcd_metoo_amount(rcd_metoo_amount);
//		recordVO.setMb_id(mb_id);
		return recordVO;
	}

	public void deleteRecord(String rcd_no) {
		dao.delete(rcd_no);
	}

	public RecordVO getOneRecord(String rcd_no) {
//System.out.println(rcd_no);
		return dao.findByPrimaryKey(rcd_no);
	}

	public List<RecordVO> getAll() {
		return dao.getAll();
	}
	
	public List<RecordVO> getByMb_id(String mb_id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("mb_id", new String[] { mb_id });
		return dao.getAllUWish(map,"RCD_NO desc");
	}
	
	public List<RecordVO> getByMbs_id(String[] mbs_id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("mb_id", mbs_id);
		return dao.getAllUWish(map,"RCD_NO desc");
	}
	
	public List<android.com.record.model.RecordVO> getAllByMb_id(String mb_id) {
		List comment = null;
		return comment;
	}
}
