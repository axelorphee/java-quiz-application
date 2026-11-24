<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Statistiques</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
    <h1>Statistiques</h1>
    <ul>
        <li>Total d’utilisateurs : ${totalUsers}</li>
        <li>Total de tests : ${totalTests}</li>
        <li>Moyenne des scores : ${averageScore}</li>
    </ul>
    <h2>Top 5 utilisateurs</h2>
    <ul>
        <c:forEach var="user" items="${topUsers}">
            <li>${user.nom} (${user.email})</li>
        </c:forEach>
    </ul>
    <a href="result">Retour</a>
</body>
</html>
