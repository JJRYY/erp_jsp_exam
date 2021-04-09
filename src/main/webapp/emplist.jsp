<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
<link rel="stylesheet" href="css/emplist.css">
</head>
<body>
	<h2>사원목록</h2>
	<table>
		<thead>
			<tr>
				<th>사원번호</th><th>사원명</th><th>직책</th><th>직속상관</th><th>급여</th><th>부서</th><th>입사일</th>
			</tr>
		</thead>	
		<tbody>
		<c:forEach var="emp" items="${list }">
			<tr>
				<td>${emp.no }</td>
				<td>${emp.name }</td>
				<td>${emp.title.name }</td>
				<td>
				<c:if test="${emp.manager.no eq 0 }">
					
				</c:if>
				<c:if test="${emp.manager.no ne 0 }">
					${emp.manager.name } (${emp.manager.no })
				</c:if>
				</td>
				<td><fmt:formatNumber value="${emp.salary }" pattern="#,###" /></td>
				<td>${emp.dept.name }</td>
				<td><fmt:formatDate value="${emp.hireDate }" type="date" pattern="yyyy년 MM월 dd일" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>