<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des tests</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="text-center my-4">Liste des tests</h1>
    <table class="table table-striped table-bordered">
        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Thème</th>
                <th>Note de passage</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>${fn:escapeXml(test.id)}</td>
                    <td>${fn:escapeXml(test.theme)}</td>
                    <td>${fn:escapeXml(test.passingGrade)}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-center mt-4">
        <a href="result" class="btn btn-outline-primary">Retour</a>
    </div>
</body>
</html>
