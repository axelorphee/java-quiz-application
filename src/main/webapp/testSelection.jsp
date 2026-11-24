<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sélection de Test</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Sélectionnez un test</h1>
    <form action="selectTest" method="post">
        <ul>
            <c:forEach var="test" items="${tests}">
                <li>
                    <label>
                        <input type="radio" name="testId" value="${test.id}" required>
                        ${test.theme} (Note de passage : ${test.passingGrade})
                    </label>
                </li>
            </c:forEach>
        </ul>
        <button type="submit">Commencer</button>
    </form>
</body>
</html>
