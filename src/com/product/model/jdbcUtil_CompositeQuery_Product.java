/* 1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位*/

package com.product.model;

import java.util.*;

public class jdbcUtil_CompositeQuery_Product {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		
		String aCondition = null;
		
	 if ("pd_name".equals(columnName) || "pd_typeNo".equals(columnName)) // 用於varchar
		 
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {

		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key) && !"lowPrice".equals(key) && !"highPrice".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				
				
				if (count == 1) {
					whereCondition.append(" where " + aCondition);
				}

				else {
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
			
			}
		}

		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("pd_name", new String[] { "鞋" });
		map.put("pd_typeNo", new String[] { "PTN00001" });
//		map.put("lowPrice", new String[] { "1" });
//		map.put("highPrice", new String[] { "3000" });
		
		String lowPrice = map.get("lowPrice")[0];
		String highPrice = map.get("highPrice")[0];
		System.out.println(map.size());
		System.out.println(map.get("pd_name")[0]);
		System.out.println(map.get("pd_typeNo")[0]);
		System.out.println(lowPrice);
        System.out.println(highPrice);
        
        
        if(!lowPrice.equals("") && !highPrice.equals("") && map.get("pd_name")[0].equals("") && map.get("pd_typeNo")[0].equals("")) {
			System.out.println("純價錢");
			String finalSQL = "select * from product " + "where" + "pd_price between" + " " + lowPrice + " " + "and" + " " +highPrice + " "+ "order by pd_no";
			System.out.println("●●finalSQL = " + finalSQL);
        }else if(!lowPrice.equals("") && !highPrice.equals("") && (!map.get("pd_name")[0].equals("") ||!map.get("pd_typeNo")[0].equals(""))) {
			System.out.println("有價錢");
			String finalSQL = "select * from product " + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map) + 
					"and" + " " + "pd_price between" + " " + lowPrice + " " + "and" + " " +highPrice + " "+ "and pd_status = 2" + " " + "order by pd_no";
			System.out.println("●●finalSQL = " + finalSQL);
		
		
		}else {
		
		System.out.println("全無");
		String finalSQL = "select * from product " + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
				+ "where pd_status = 2" + " " +"order by pd_no";
		System.out.println("●●finalSQL = " + finalSQL);
		}
		
	}
}
