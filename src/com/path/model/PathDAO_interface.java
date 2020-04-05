package com.path.model;

import java.util.List;

public interface PathDAO_interface {
	 public void insert(PathVO pathVO);
     public void update(PathVO pathVO);
     public void delete(String path_no);
     public PathVO findByPrimaryKey(String path_no);
     public List<PathVO> getAll();
}
