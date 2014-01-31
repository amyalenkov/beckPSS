<%--
  Created by IntelliJ IDEA.
  User: amyalenkov
  Date: 08.01.14
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#updatePlayersInTeam').click(function(){
                $.ajax({
                    url: "/updatePlayersInTeam",
                    type: "GET",
                    data: "teamName="+$('#team').text(),
                    success: function(html){
                        location.reload();
                    }
                });
            });
        });
    </script>
</head>
<body>
<div id="team">${team.name}</div>
<table>
    <tr>
        <td>id</td>
        <td>firstName</td>
        <td>lastName</td>
        <td>data</td>
        <td>country</td>
        <td>number</td>
    </tr>
    <%

    %>
    <c:forEach items="${players}" var="player">
    <tr>
        <td>${player.player.id}</td>
        <td>${player.player.firstName}</td>
        <td>${player.player.lastName}</td>
        <td>${player.player.data}</td>
        <td>${player.player.counrty}</td>
        <td>${player.number}</td>
    </tr>
    </c:forEach>

</table>
<input type="button" id="updatePlayersInTeam" value="Обновить состав">

</body>
</html>
