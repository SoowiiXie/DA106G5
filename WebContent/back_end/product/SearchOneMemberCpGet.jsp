<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="POST" action="<%=request.getContextPath()%>/CpGetServlet"
		name="form1">

		<table>

			<tr>

				<td>會員名稱：</td>
				<td><input type="TEXT" name="mb_id"></td>



			</tr>



		</table>
		<input type="hidden" name="action" value="searchMemberCpGet"> <input
			type="submit" name="Submit" value="搜尋持有優惠券">
	</form>

	soowii123 xuan123 michael123 vain123 yiwen123 weijhih123

</body>
</html>