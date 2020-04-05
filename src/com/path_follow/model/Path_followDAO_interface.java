package com.path_follow.model;

import java.util.List;

public interface Path_followDAO_interface {
	 public void insert(Path_followVO path_followVO);
     public void update(Path_followVO path_followVO);
     public void delete(String path_no, String mb_id);
     public Path_followVO findByPrimaryKey(String path_no, String mb_id);
     public List<Path_followVO> getAll();
}
