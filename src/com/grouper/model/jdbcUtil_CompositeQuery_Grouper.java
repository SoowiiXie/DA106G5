/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.grouper.model;

import java.util.*;

public class jdbcUtil_CompositeQuery_Grouper {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("grp_personmax".equals(columnName)||"grp_personmin".equals(columnName) || "grp_personcouunt".equals(columnName) ||
				"grp_status".equals(columnName) || "grp_follow".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("grp_no".equals(columnName) || "mb_id".equals(columnName) || "loc_no".equals(columnName) || 
					"grp_name".equals(columnName) || "grp_content".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("grp_applystart".equals(columnName) || "grp_applyend".equals(columnName) || "grp_start".equals(columnName) || "grp_end".equals(columnName) )                            // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd hh:mm:ss')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("grp_no", new String[] { "grp00001" });
		map.put("mb_id", new String[] { "soowii123" });
		map.put("loc_no", new String[] { "loc00001" });
		map.put("grp_applystart", new String[] { "2020-05-15 08:30:00" });
		map.put("grp_applyend", new String[] { "2020-05-15 18:30:00" });
		map.put("grp_start", new String[] { "2020-05-10 08:30:00" });
		map.put("grp_end", new String[] { "2020-05-13 20:30:00" });
		map.put("grp_name", new String[] { "luck ball go" });
		map.put("grp_content", new String[] { "gogogo" });
		map.put("grp_personmax", new String[] { "30" });
		map.put("grp_personmin", new String[] { "5" });
		map.put("grp_personcount", new String[] { "15" });
		map.put("grp_status", new String[] { "1" });
		map.put("grp_follow", new String[] { "30" });	
		map.put("action", new String[] { "getXXX" });
		// 注意Map裡面會含有action的key

		String finalSQL = "select * from grouper "
				          + jdbcUtil_CompositeQuery_Grouper.get_WhereCondition(map)
				          + "order by grp_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
