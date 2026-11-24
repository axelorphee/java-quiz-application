package servlet;

import model.User;
import service.UserService;
import service.TestService;
import service.ResultService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {
    // Utilisation des instances Singleton pour les services
    private final UserService userService = UserService.getInstance();
    private final TestService testService = TestService.getInstance();
    private final ResultService resultService = ResultService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Nombre total d’utilisateurs
            int totalUsers = userService.getAllUsers().size();

            // Nombre total de tests
            int totalTests = testService.getAllTests().size();

            // Moyenne des scores obtenus
            double averageScore = resultService.getAverageScore();

            // Top 5 des utilisateurs
            List<User> topUsers = resultService.getTopUsers(5);

            // Transmettre les données à la JSP
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalTests", totalTests);
            request.setAttribute("averageScore", averageScore);
            request.setAttribute("topUsers", topUsers);

            request.getRequestDispatcher("statistics.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue est survenue lors de la récupération des statistiques.");
            request.getRequestDispatcher("statistics.jsp").forward(request, response);
        }
    }
}
