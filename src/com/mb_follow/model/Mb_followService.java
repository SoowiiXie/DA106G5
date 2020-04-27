package com.mb_follow.model;

import java.util.List;

public class Mb_followService {
	private Mb_followDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public Mb_followService() {
		dao = new Mb_followDAO();
	}
	
	public String[] getByMb_id(String mb_id) {
		List<Mb_followVO> list = dao.findByMbId(mb_id);
		String[] mbs_id = new String[list.size()];
//		for (int i = 0; i < mbs_id.length; i++) {
//			mbs_id[i]=list[i].getMb_id();
//		}
		int i =0;
		for(Mb_followVO follow:list) {
			mbs_id[i]=follow.getMb_id_followed();
			i++;
		}
		return mbs_id;
	}
	
	public void insertByString(String mb_id, String mb_id_followed) {
		dao.insertByString(mb_id, mb_id_followed);
	}
	
	public void deleteByString(String mb_id, String mb_id_followed) {
		dao.deleteByString(mb_id, mb_id_followed);
	}
	
	// 是否有追蹤
	public boolean hasFollow(String mb_id, String mb_id_followed) {
		return dao.hasFollow(mb_id, mb_id_followed);
	}
}
