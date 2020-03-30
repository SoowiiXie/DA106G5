package com.common;

public class Common {
	// 連線至本機Oracle
	 public final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	public final static String URL = "jdbc:oracle:thin:@10.211.55.4:1521:xe";
	public final static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	// 必須先在Oracle DB上建立BOOKSHOP_JDBC使用者，並授予連線與建立表格權利
	public final static String USER = "DA106G5";
	public final static String PASSWORD = "DA106G5";
}
