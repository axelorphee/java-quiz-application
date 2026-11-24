package servlet;

import model.Test;
import service.TestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listTests")
public class ListTestsServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final TestService testService = TestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer tous les tests
            List<Test> tests = testService.getAllTests();
            request.setAttribute("tests", tests);

            // Rediriger vers la JSP pour affichage
            request.getRequestDispatcher("listTests.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des tests.");
            request.getRequestDispatcher("listTests.jsp").forward(request, response);
        }
    }
}
