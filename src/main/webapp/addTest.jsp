<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un test</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="text-center my-4">Ajouter un test</h1>
    <form action="addTest" method="post" class="form-group">
        <label for="testName">Nom du test :</label>
        <input type="text" id="testName" name="testName" class="form-control" required>
        <br>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
    </form>
    <br>
    <a href="result" class="btn btn-outline-secondary">Retour</a>
</body>
</html>
