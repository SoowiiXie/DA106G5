package com.record.model;

import java.util.List;

public class RecordService {
	private RecordDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public RecordService() {
		dao = new RecordJDBCDAO();
	}

	public RecordVO addRecord(java.sql.Date rcd_uploadtime, String rcd_content, String path_no, String mb_id) {

		RecordVO recordVO = new RecordVO();

		recordVO.setRcd_uploadtime(rcd_uploadtime);
		recordVO.setRcd_content(rcd_content);
		recordVO.setPath_no(path_no);
		recordVO.setMb_id(mb_id);
		System.out.println("here");
		System.out.println(recordVO.getMb_id());
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
		return recordVO;
	}

	public void deleteRecord(String rcd_no) {
		dao.delete(rcd_no);
	}

	public RecordVO getOneRecord(String rcd_no) {
//		System.out.println(rcd_no);
		return dao.findByPrimaryKey(rcd_no);
	}

	public List<RecordVO> getAll() {
		return dao.getAll();
	}
}
