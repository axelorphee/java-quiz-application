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

@WebServlet("/selectTest")
public class TestSelectionServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final TestService testService = TestService.getInstance();

    // Affiche la page de sélection des tests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Test> tests = testService.getAllTests();

            // Log pour vérifier les tests récupérés
            System.out.println("Tests récupérés dans TestSelectionServlet (doGet) :");
            for (Test test : tests) {
                System.out.println("ID=" + test.getId() + ", Thème=" + test.getTheme() + ", Note de passage=" + test.getPassingGrade());
            }

            // Transmet les tests à la JSP
            request.setAttribute("tests", tests);
            // Redirection vers la JSP
            request.getRequestDispatcher("testSelection.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des tests.");
            request.getRequestDispatcher("testSelection.jsp").forward(request, response);
        }
    }

    // Gère la sélection d'un test par l'utilisateur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testIdStr = request.getParameter("testId");

        if (testIdStr == null || testIdStr.isEmpty()) {
            request.setAttribute("error", "Veuillez sélectionner un test.");
            doGet(request, response);
            return;
        }

        try {
            int testId = Integer.parseInt(testIdStr);

            // Enregistre l'ID du test sélectionné dans la session
            request.getSession().setAttribute("selectedTestId", testId);

            // Redirection vers la page du quiz
            response.sendRedirect("quiz");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Test sélectionné invalide.");
            doGet(request, response);
        }
    }
}
