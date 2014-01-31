<%--
  Created by IntelliJ IDEA.
  User: amyalenkov
  Date: 20.01.14
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
матчи
<table >
    <tr>
        <td>id</td>
        <td>tournament</td>
        <td>stage</td>
        <td>team1</td>
        <td>team2</td>
        <td>data</td>
        <td>referee</td>
    </tr>
<c:forEach items="${matches}" var="match">
    <tr>
        <td>${match.id}</td>
        <td>${match.tournament.id}</td>
        <td>${match.stage}</td>
        <td>${match.team1.id}</td>
        <td>${match.team2.id}</td>
        <td>${match.data}</td>
        <td>${match.referee.lastName}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
