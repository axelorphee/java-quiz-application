<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
    <link href="css/styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Quiz</h1>
    <form action="quiz" method="post">
        <c:forEach var="entry" items="${quizData}">
            <div>
                <p><b>${entry.key.questionText}</b></p>
                <c:forEach var="answer" items="${entry.value}">
                    <label>
                        <input type="radio" name="question_${entry.key.id}" value="${answer.id}" required>
                        ${answer.answerText}
                    </label><br>
                </c:forEach>
            </div>
        </c:forEach>
        <button type="submit">Soumettre</button>
    </form>
</body>
</html>
