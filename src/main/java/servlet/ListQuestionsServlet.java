package servlet;

import model.Question;
import service.QuestionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listQuestions")
public class ListQuestionsServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final QuestionService questionService = QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer toutes les questions
            List<Question> questions = questionService.getAllQuestions();
            request.setAttribute("questions", questions);

            // Rediriger vers la JSP pour affichage
            request.getRequestDispatcher("listQuestions.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des questions.");
            request.getRequestDispatcher("listQuestions.jsp").forward(request, response);
        }
    }
}
