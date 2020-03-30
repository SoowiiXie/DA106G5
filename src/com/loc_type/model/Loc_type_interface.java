package com.loc_type.model;

import java.util.*;

public interface Loc_type_interface {
	public void insert(Loc_typeVO loc_typeVO);

	public void update(Loc_typeVO loc_typeVO);

	public void delete(String loc_typeno);

	public Loc_typeVO findByPrimaryKey(String loc_typeno);

	public List<Loc_typeVO> getAll();
}