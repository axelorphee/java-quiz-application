<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une question</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="text-center my-4">Ajouter une question</h1>
    <form action="addQuestion" method="post" class="form-group">
        <label for="testId">Sélectionnez un test :</label>
        <select name="testId" id="testId" class="form-control" required>
            <c:forEach var="test" items="${tests}">
                <option value="${test.id}">${test.theme}</option>
            </c:forEach>
        </select>
        <br>
        <label for="questionText">Texte de la question :</label>
        <input type="text" id="questionText" name="questionText" class="form-control" required>
        <br>
        <label for="correctAnswer">Réponse correcte :</label>
        <input type="text" id="correctAnswer" name="correctAnswer" class="form-control" required>
        <br>
        <label for="wrongAnswer">Réponse incorrecte :</label>
        <input type="text" id="wrongAnswer" name="wrongAnswer" class="form-control" required>
        <br>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
    </form>
    <br>
    <a href="result" class="btn btn-outline-secondary">Retour</a>
</body>
</html>
