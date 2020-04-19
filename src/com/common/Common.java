package com.common;

public class Common {
	// 連線至本機Oracle
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	public final static String URL = "jdbc:oracle:thin:@10.211.55.4:1521:xe";
	public final static String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	// 必須先在Oracle DB上建立BOOKSHOP_JDBC使用者，並授予連線與建立表格權利
	public final static String USER = "DA106G5";
	public final static String PASSWORD = "DA106G5";
	
	// 連線至heroku PostgreSQL			//postgres://<username>:<password>@<host>:<port>/<dbname>
	public final static String URL_PG = "postgres://tlnlkxrtnbepdl:2a372ee7bedb7e93309cb56336a42fe8824885adb6a6509d27d86cdba914c5d3@ec2-52-86-73-86.compute-1.amazonaws.com:5432/daqfqhdshludoq";
												//jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
	public final static String DRIVER_CLASS_PG = "jdbc:postgresql://ec2-52-86-73-86.compute-1.amazonaws.com:5432/daqfqhdshludoq?user=tlnlkxrtnbepdl&password=2a372ee7bedb7e93309cb56336a42fe8824885adb6a6509d27d86cdba914c5d3";
	public final static String USER_PG = "tlnlkxrtnbepdl";
	public final static String PASSWORD_PG = "2a372ee7bedb7e93309cb56336a42fe8824885adb6a6509d27d86cdba914c5d3";
}

