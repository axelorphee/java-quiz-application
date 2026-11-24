package servlet;

import model.User;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer tous les utilisateurs
            List<User> users = userService.getAllUsers();
            request.setAttribute("users", users);

            // Rediriger vers la JSP pour affichage
            request.getRequestDispatcher("listUsers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des utilisateurs.");
            request.getRequestDispatcher("listUsers.jsp").forward(request, response);
        }
    }
}
