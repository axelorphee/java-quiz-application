package servlet;

import model.User;
import service.ResultService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final ResultService resultService = ResultService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données nécessaires depuis la session
        Integer score = (Integer) request.getSession().getAttribute("score");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        Integer testId = (Integer) request.getSession().getAttribute("selectedTestId");

        // Validation des données
        if (score == null || currentUser == null || testId == null) {
            response.sendRedirect("selectTest"); // Redirige si des informations manquent
            return;
        }

        try {
            // Enregistrer le résultat dans la base de données
            resultService.addResult(currentUser.getId(), testId, score);

            // Transmettre le score à la JSP
            request.setAttribute("score", score);
            request.getRequestDispatcher("results.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de l'enregistrement des résultats.");
            request.getRequestDispatcher("results.jsp").forward(request, response);
        }
    }
}
