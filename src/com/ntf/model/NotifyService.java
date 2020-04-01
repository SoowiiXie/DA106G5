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
	
	public NotifyVO updateNotify(String ntf_no, String mb_id,
			String ntf_content, Integer ntf_status) {
		
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNtf_no(ntf_no);
		notifyVO.setMb_id(mb_id);
		notifyVO.setNtf_content(ntf_content);
		notifyVO.setNtf_status(ntf_status);
		dao.update(notifyVO);
		return notifyVO;
	}

	public NotifyVO getOneNotify(String ntf_no) {
		return dao.findByPrimaryKey(ntf_no);
	}

	public List<NotifyVO> getAll() {
		return dao.getAll();
	}
}
