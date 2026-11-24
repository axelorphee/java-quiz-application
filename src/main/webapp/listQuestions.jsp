<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des questions</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="text-center my-4">Liste des questions</h1>
    <table class="table table-striped table-bordered">
        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>ID du Test</th>
                <th>Texte de la question</th>
                <th>Niveau</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="question" items="${questions}">
                <tr>
                    <td>${fn:escapeXml(question.id)}</td>
                    <td>${fn:escapeXml(question.testId)}</td>
                    <td>${fn:escapeXml(question.questionText)}</td>
                    <td>${fn:escapeXml(question.level)}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-center mt-4">
        <a href="result" class="btn btn-outline-primary">Retour</a>
    </div>
</body>
</html>
