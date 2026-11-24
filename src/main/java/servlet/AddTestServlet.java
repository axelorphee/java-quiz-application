package servlet;

import model.Test;
import service.TestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addTest")
public class AddTestServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final TestService testService = TestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Afficher la page d'ajout de test
        request.getRequestDispatcher("addTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testName = request.getParameter("testName").trim();

        // Validation du nom du test
        if (testName.isEmpty()) {
            request.setAttribute("error", "Le nom du test est requis.");
            request.getRequestDispatcher("addTest.jsp").forward(request, response);
            return;
        }

        try {
            // Ajouter le test dans la base
            Test newTest = testService.createTest(testName, 10); // Par défaut, une note de passage de 10
            System.out.println("Nouveau test créé : ID=" + newTest.getId() + ", Thème=" + newTest.getTheme());

            // Rediriger vers la page d'ajout de question
            response.sendRedirect("addQuestion");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue s'est produite lors de l'ajout du test.");
            request.getRequestDispatcher("addTest.jsp").forward(request, response);
        }
    }
}
