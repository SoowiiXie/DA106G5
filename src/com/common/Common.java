package com.common;

public class Common {
	// 連線至本機Oracle
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	public final static String URL = "jdbc:oracle:thin:@10.211.55.4:1521:xe";
	public final static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	// 必須先在Oracle DB上建立BOOKSHOP_JDBC使用者，並授予連線與建立表格權利
	public final static String USER = "DA106G5";
	public final static String PASSWORD = "DA106G5";
	
	// 連線至heroku PostgreSQL		   //postgres://<username>    :<password>                                                      @<host>                                 :<port>/<dbname>
	public final static String URI_PG = "postgres://rcccchvvxjnxbw:42bc0d8ef563d2f91b1b2bfb222fdcc3900f9368f2ed287c30b06fbbcf7e6469@ec2-3-231-16-122.compute-1.amazonaws.com:5432/d5o37ss0mrmndl";
									   //jdbc:postgresql://<host>                                 :<port>/<dbname>?user=<username>&password=<password>
	public final static String URL_PG = "jdbc:postgresql://ec2-3-231-16-122.compute-1.amazonaws.com";
	public final static String DRIVER_CLASS_PG = "org.postgresql.Driver";
	public final static String USER_PG = "rcccchvvxjnxbw";
	public final static String PASSWORD_PG = "42bc0d8ef563d2f91b1b2bfb222fdcc3900f9368f2ed287c30b06fbbcf7e6469";
}

