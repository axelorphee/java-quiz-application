<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Connexion</h1>
    <form action="login" method="post">
        <label for="userId">ID Utilisateur :</label>
        <input type="text" id="userId" name="userId" required>
        <button type="submit">Se connecter</button>
    </form>
    <p>
        Pas encore inscrit ? <a href="register.jsp">S'inscrire ici</a>.
    </p>
    <p class="error">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</body>
</html>
