package servlet;

import model.User;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");

        // Validation de l'entrée utilisateur
        if (userIdStr == null || userIdStr.isEmpty()) {
            request.setAttribute("error", "L'ID utilisateur est requis !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr.trim());

            User user = userService.getUserById(userId);
            if (user == null) {
                request.setAttribute("error", "Utilisateur introuvable !");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // Enregistrer l'utilisateur dans la session
                request.getSession().setAttribute("currentUser", user);
                response.sendRedirect("selectTest");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "L'ID utilisateur doit être un nombre valide.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue est survenue.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
