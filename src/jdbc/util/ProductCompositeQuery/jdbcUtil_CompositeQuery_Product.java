/* 1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位*/

package jdbc.util.ProductCompositeQuery;

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
		map.put("pd_name", new String[] { "" });
		map.put("pd_typeNo", new String[] { "PTN00003" });
		map.put("lowPrice", new String[] { "" });
		map.put("highPrice", new String[] { "" });
		String lowPrice = map.get("lowPrice")[0];
		String highPrice = map.get("highPrice")[0];
		System.out.println(map.size());
		System.out.println(lowPrice);
        System.out.println(highPrice);
        
        
       
        
        
        
		if(!lowPrice.equals("") && !highPrice.equals("")) {
			System.out.println("有價錢");
			String finalSQL = "select * from product " + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map) + 
					"and" + " " + "pd_price between" + " " + lowPrice + " " + "and" + " " +highPrice + " "+ "order by pd_no";
			System.out.println("●●finalSQL = " + finalSQL);
		
		}else {
		
		System.out.println("無價錢");
		String finalSQL = "select * from product " + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
				+ "order by pd_no";
		System.out.println("●●finalSQL = " + finalSQL);
		}
		
	}
}
