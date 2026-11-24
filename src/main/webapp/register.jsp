<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Inscription</h1>
    <form action="register" method="post">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required><br>

        <label for="dateOfBirth">Date de naissance :</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required><br>

        <button type="submit">S'inscrire</button>
    </form>
</body>
</html>
