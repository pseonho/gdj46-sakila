<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rewardReportForm</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	 <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
         <table class="table">
         <thead class="thead-dark">
		<h1>rewardReport</h1>
			<form class="form-inline" method="post" action="<%=request.getContextPath()%>/rewardsReport.jsp">
				<table class="table">
					<tr>
						<td>Monthly_Purchase</td>
						<td><input type="text" name="minMonthlyPurchase"></td>
					</tr>
					<tr>
						<td>DollarAmount_Purchase</td>
						<td><input type="text" name="minDollarAmountPurchase"></td>
					</tr>
				</table>
					<button class="btn btn-outline-dark" type="submit">입력 </button>
			</form>
	</div>
</body>
</html> 