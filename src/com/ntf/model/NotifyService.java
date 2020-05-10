package com.ntf.model;

import java.util.List;

public class NotifyService {

	private NotifyDAO_interface dao;
	
	public NotifyService() {
		dao = new NotifyDAO();
	}
	
	public NotifyVO addNotify(String mb_id, String ntf_content) {
		
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setMb_id(mb_id);
		notifyVO.setNtf_content(ntf_content);
		dao.insert(notifyVO);
		return notifyVO;
	}
	
	public NotifyVO updateNotify(String ntf_no, String mb_id,String ntf_content, Integer ntf_status) {
		
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNtf_no(ntf_no);
		notifyVO.setMb_id(mb_id);
		notifyVO.setNtf_content(ntf_content);
		notifyVO.setNtf_status(ntf_status);
		dao.update(notifyVO);
		return notifyVO;
	}
	
	public void updateNotifyStatus(Integer ntf_status, String ntf_no) {
		
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNtf_status(ntf_status);
		notifyVO.setNtf_no(ntf_no);
		dao.updateStatus(notifyVO);
	}

	public NotifyVO getOneNotify(String ntf_no) {
		return dao.findByPrimaryKey(ntf_no);
	}

	public List<NotifyVO> getAll() {
		return dao.getAll();
	}	
	
	public List<NotifyVO> getAllByMb_id(String mb_id) {
		return dao.getAllByMb_id(mb_id);
	}
	
	public List<NotifyVO> getAllByMb_idReallyAll(String mb_id) {
		return dao.getAllByMb_idReallyAll(mb_id);
	}
	
	public Integer countNotReads(String mb_id) {
    	return dao.countNotReads(mb_id);
    }
}
