package servlet;

import model.Question;
import model.Answer;
import service.QuestionService;
import service.AnswerService;
import service.TestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    // Utilisation des instances Singleton des services
    private final TestService testService = TestService.getInstance();
    private final QuestionService questionService = QuestionService.getInstance();
    private final AnswerService answerService = AnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer tous les tests pour affichage dans le formulaire
        request.setAttribute("tests", testService.getAllTests());
        request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("questionText").trim();
        String correctAnswer = request.getParameter("correctAnswer").trim();
        String wrongAnswer = request.getParameter("wrongAnswer").trim();

        // Validation de base
        if (questionText.isEmpty() || correctAnswer.isEmpty() || wrongAnswer.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont requis !");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
            return;
        }

        // Nettoyage des entrées pour éviter les attaques XSS
        questionText = org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(questionText);
        correctAnswer = org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(correctAnswer);
        wrongAnswer = org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(wrongAnswer);

        try {
            // Ajouter la question et les réponses
            int testId = Integer.parseInt(request.getParameter("testId"));
            Question question = questionService.addQuestion(testId, questionText, "Facile");
            answerService.addAnswer(question.getId(), correctAnswer, true);
            answerService.addAnswer(question.getId(), wrongAnswer, false);

            response.sendRedirect("result");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "L'identifiant du test est invalide !");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue s'est produite.");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        }
    }
}
