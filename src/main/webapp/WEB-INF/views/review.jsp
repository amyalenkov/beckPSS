<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.pss.beck.domain.Tournament" %>
<%--
  Created by IntelliJ IDEA.
  User: amyalenkov
  Date: 07.01.14
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#tours").change(function () {
                var id = $(this).val();
                var dataString = 'id=' + id;
                $.getJSON('/choiseTour?'+dataString, function (data) {
                    $.each(data, function (key, val) {
                        $('#teams').append(
                                '<tr>' +
                                    '<td>'+val.teamId+'</td>' +
                                    '<td><a href="teams/'+val.teamId+'">'+val.name+'</td>' +
                                    '<td>'+val.counrty+'</td>' +
                                '</tr>');
                    });
                });
            });
            $('#updateTeams').click(function(){
                var idTour = $("#tours :selected").val();
                if(idTour != "none"){
                    $.ajax({
                        url: "/updateTeams",
                        type: "GET",
                        data: "id="+idTour,
                        success: function(html){
//                            location.reload();
                        }
                    });
                }
            });

        });
    </script>
    <title></title>
</head>
<body>
<select id="tours">
    <option value="none">Выберите турнир</option>
    <c:forEach items="${tours}" var="tour">
        <option value="${tour.id}">${tour.name} ${tour.data}</option>
    </c:forEach>
</select>

<table id="teams">
    <tr>
        <td>id</td>
        <td>country</td>
        <td>name</td>
    </tr>
</table>
<input type="button" id="updateTeams" value="Обновить команды">

</body>
</html>
