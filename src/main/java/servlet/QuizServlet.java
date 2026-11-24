package servlet;

import config.PointConfig;
import model.Answer;
import model.Question;
import service.AnswerService;
import service.QuestionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    // Utilisation des instances Singleton pour les services
    private final QuestionService questionService = QuestionService.getInstance();
    private final AnswerService answerService = AnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du test depuis la session
        Integer testId = (Integer) request.getSession().getAttribute("selectedTestId");

        if (testId == null) {
            response.sendRedirect("selectTest"); // Redirige si aucun test n'est sélectionné
            return;
        }

        // Récupérer les questions du test
        List<Question> questions = questionService.getQuestionsByTestId(testId);

        // Préparer une map des questions et de leurs réponses
        Map<Question, List<Answer>> quizData = new HashMap<>();
        for (Question question : questions) {
            List<Answer> answers = answerService.getAnswersByQuestionId(question.getId());
            quizData.put(question, answers);
        }

        // Transmettre les données à la JSP
        request.setAttribute("quizData", quizData);
        request.getRequestDispatcher("quiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer testId = (Integer) request.getSession().getAttribute("selectedTestId");

        if (testId == null) {
            response.sendRedirect("selectTest");
            return;
        }

        List<Question> questions = questionService.getQuestionsByTestId(testId);
        int score = 0;

        for (Question question : questions) {
            String answerIdStr = request.getParameter("question_" + question.getId());

            // Validation des réponses soumises
            if (answerIdStr != null && !answerIdStr.isEmpty()) {
                try {
                    int answerId = Integer.parseInt(answerIdStr);

                    // Récupérer la réponse soumise
                    Answer answer = answerService.getAnswersByQuestionId(question.getId()).stream()
                            .filter(a -> a.getId() == answerId)
                            .findFirst()
                            .orElse(null);

                    if (answer != null) {
                        if (answer.isCorrect()) {
                            score += PointConfig.POINTS_FOR_CORRECT_ANSWER; // Points configurés pour une bonne réponse
                        } else {
                            score += PointConfig.POINTS_FOR_WRONG_ANSWER; // Points configurés pour une mauvaise réponse
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Réponse soumise invalide pour la question ID=" + question.getId());
                }
            }
        }

        request.getSession().setAttribute("score", score);
        response.sendRedirect("result");
    }
}
