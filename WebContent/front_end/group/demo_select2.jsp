<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Demo select2</title>
<link href="<%= request.getContextPath() %>/js/select2/dist/css/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/select2/dist/js/select2.min.js"></script>
</head>
<body>
	<div style="width:520px;margin:0px auto;margin-top:30px;height:500px;">
		  <h2>Select Box with Search Option Jquery Select2.js</h2>
		  <select class="myselect" style="width:500px;">
			  <option>Laravel</option>
			  <option>Laravel ACL</option>
			  <option>Laravel PDF</option>
			  <option>Laravel Helper</option>
			  <option>Laravel API</option>
			  <option>Laravel CRUD</option>
			  <option>Laravel Angural JS Crud</option>
			  <option>C++</option>
		  </select>
	</div>
</body>
<script type="text/javascript">
      $(".myselect").select2();
</script>
</html>