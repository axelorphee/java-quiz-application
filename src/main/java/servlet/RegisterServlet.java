package servlet;

import model.User;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom").trim();
        String email = request.getParameter("email").trim();
        String dobStr = request.getParameter("dob").trim();

        // Validation de base
        if (nom.isEmpty() || email.isEmpty() || dobStr.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont requis !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validation de l'email
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            request.setAttribute("error", "Adresse email invalide.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            Date dateOfBirth = Date.valueOf(dobStr);

            // Enregistrer l'utilisateur
            userService.createUser(nom, email, dateOfBirth);

            // Redirection vers la page de connexion après l'inscription réussie
            response.sendRedirect("login.jsp");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Date de naissance invalide (format attendu : AAAA-MM-JJ).");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue s'est produite.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
