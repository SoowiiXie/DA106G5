package com.msg.model;

import java.util.List;

public class MessageService {

	private MessageDAO_interface dao;
	
	public MessageService() {
		dao = new MessageDAO();
	}
	
	public MessageVO addMessage(String mb_id_1, String mb_id_2, String msg_content) {
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMb_id_1(mb_id_1);
		messageVO.setMb_id_2(mb_id_2);
		messageVO.setMsg_content(msg_content);
		dao.insert(messageVO);
		return messageVO;
	}
	
	public MessageVO updateMessage(String msg_no, String mb_id_1, String mb_id_2,
			String msg_content, Integer msg_status) {
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMsg_no(msg_no);
		messageVO.setMb_id_1(mb_id_1);
		messageVO.setMb_id_2(mb_id_2);
		messageVO.setMsg_content(msg_content);
		messageVO.setMsg_status(msg_status);
		dao.update(messageVO);
		return messageVO;
	}
	
	public void deleteMessage(String msg_no) {
		dao.delete(msg_no);
	}

	public MessageVO getOneMessage(String msg_no) {
		return dao.findByPrimaryKey(msg_no);
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}
}
