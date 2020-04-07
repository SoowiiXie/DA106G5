<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%
//取得前端送來的資料 


//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA106G5", "DA106G5"); 
  
//建立PreparedStatement物件 
PreparedStatement ps = conn.prepareStatement("select * from member where mb_id=?");
//代入資料    
 ps.setString(1, request.getParameter("mb_id"));
//執行PreparedStatement
ResultSet rs = ps.executeQuery();
  
//取回一筆資料
if(rs.next()){
	out.print(0);
}else{
	out.print(1);
};
 
//關閉ResultSet物件 	
rs.close();
//關閉Statement物件    
ps.close();
//關閉Connection物件 	 
conn.close();
%>   


