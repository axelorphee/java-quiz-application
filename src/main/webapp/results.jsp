<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultats</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="text-center my-4">Résultats</h1>
    <p class="text-center">Votre score est : <span class="fw-bold">${fn:escapeXml(score)}</span></p>

    <h2 class="text-center my-4">Actions disponibles</h2>
    <div class="d-flex flex-column align-items-center">
        <form action="listUsers" method="get">
            <button type="submit" class="btn btn-primary">Lister tous les utilisateurs</button>
        </form>
        <form action="listTests" method="get">
            <button type="submit" class="btn btn-primary">Lister tous les tests</button>
        </form>
        <form action="listQuestions" method="get">
            <button type="submit" class="btn btn-primary">Lister toutes les questions</button>
        </form>
        <form action="register.jsp" method="get">
            <button type="submit" class="btn btn-success">Ajouter un utilisateur</button>
        </form>
        <form action="addQuestion" method="get">
            <button type="submit" class="btn btn-info">Ajouter une question</button>
        </form>
        <form action="addTest" method="get">
            <button type="submit">Ajouter un test</button>
        </form>
        <form action="statistics" method="get">
            <button type="submit" class="btn btn-info">Voir les statistiques</button>
        </form>
        <form action="search" method="get">
                <button type="submit">Recherche avancée</button>
        </form>

    </div>
    <div class="text-center mt-4">
        <a href="selectTest" class="btn btn-outline-primary">Rejouer</a>
        <a href="login.jsp" class="btn btn-outline-danger">Déconnexion</a>
    </div>
</body>
</html>
