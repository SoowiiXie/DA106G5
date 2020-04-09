package com.que_rpt.model;

import java.util.List;

public class Question_RPTService {
	
private Question_RPTDAO_interface dao;
	
	public Question_RPTService() {
		dao = new Question_RPTDAO();
	}
	
	public Question_RPTVO addQuestion_RPT(String mb_id, String question_content) {
		
		Question_RPTVO question_RPTVO = new Question_RPTVO();
		question_RPTVO.setMb_id(mb_id);
		question_RPTVO.setQuestion_content(question_content);
		dao.insert(question_RPTVO);
		return question_RPTVO;
	}
	
	public Question_RPTVO updateQuestion_RPT(String question_no, String mb_id,
			String question_content, Integer question_status) {
		
		Question_RPTVO question_RPTVO = new Question_RPTVO();
		question_RPTVO.setQuestion_no(question_no);
		question_RPTVO.setMb_id(mb_id);
		question_RPTVO.setQuestion_content(question_content);
		question_RPTVO.setQuestion_status(question_status);
		dao.update(question_RPTVO);
		return question_RPTVO;
	}

	public Question_RPTVO getOneQuestion_RPT(String question_no) {
		return dao.findByPrimaryKey(question_no);
	}

	public List<Question_RPTVO> getAll() {
		return dao.getAll();
	}
}
