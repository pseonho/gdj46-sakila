<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Index</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<div class="container">
<body>
	<h4>리스트</h4>
	
		<li><a href="<%=request.getContextPath()%>/storeList.jsp" class="btn btn-outline-dark" role="button">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp" class="btn btn-outline-dark" role="button">Staff List</a></li>
</body>
</div>
</html> 