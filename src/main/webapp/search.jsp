<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recherche Multi-Paramètres</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
    <h1>Recherche Multi-Paramètres</h1>
    <form action="search" method="get">
        <label for="name">Nom :</label>
        <input type="text" id="name" name="name">
        <br><br>
        <label for="email">Email :</label>
        <input type="text" id="email" name="email">
        <br><br>
        <label for="minScore">Score Minimum :</label>
        <input type="number" id="minScore" name="minScore">
        <br><br>
        <label for="birthYear">Année de Naissance :</label>
        <input type="number" id="birthYear" name="birthYear" min="1900" max="2100">
        <br><br>
        <label for="page">Page :</label>
        <input type="number" id="page" name="page" value="1" min="1">
        <br><br>
        <label for="pageSize">Résultats par page :</label>
        <input type="number" id="pageSize" name="pageSize" value="10" min="1">
        <br><br>
        <button type="submit">Rechercher</button>
    </form>

    <h2>Résultats</h2>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Date de Naissance</th>
            <th>Score Moyen</th>
        </tr>
        <c:forEach var="user" items="${results}">
            <tr>
                <td>${user.id}</td>
                <td>${user.nom}</td>
                <td>${user.email}</td>
                <td>${user.dateOfBirth}</td>
            </tr>
        </c:forEach>
    </table>


    <br>
    <c:if test="${hasPrevious}">
        <a href="?${paginationUrl}&page=${currentPage - 1}">Page précédente</a>
    </c:if>
    <c:if test="${hasNext}">
        <a href="?${paginationUrl}&page=${currentPage + 1}">Page suivante</a>
    </c:if>
</body>
</html>
