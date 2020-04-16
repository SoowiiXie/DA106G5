package com.od_detail.model;
import java.util.*;

public interface Od_detailDAO_interface {
	public Od_detailVO addOneOdDetail(Od_detailVO od_detailVO); //新增一個訂單編號的訂單明細。
//	public int update(Od_detailVO od_detailVO); //修改訂單明細，目前用不到 (X)
	public int delete_od_no_odDetail(String od_no); //刪除該訂單編號的所有訂單明細
	public List<Od_detailVO >search_one_oeder_detail(String od_no); //搜尋該訂單編號所有訂單明細
	public List<Od_detailVO >getAll();//搜尋所以全部訂單編號所有訂單明細
	
	public void addOd_detail2(Od_detailVO od_detailVO, java.sql.Connection con);
}
	