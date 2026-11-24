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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    // Utilisation de l'instance Singleton pour le service
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des paramètres de recherche
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String minScoreStr = request.getParameter("minScore");
            String birthYearStr = request.getParameter("birthYear");
            String pageStr = request.getParameter("page");
            String pageSizeStr = request.getParameter("pageSize");

            // Parsing des paramètres avec valeurs par défaut
            int minScore = (minScoreStr != null && !minScoreStr.isEmpty()) ? Integer.parseInt(minScoreStr) : 0;
            int birthYear = (birthYearStr != null && !birthYearStr.isEmpty()) ? Integer.parseInt(birthYearStr) : 0;
            int page = (pageStr != null && !pageStr.isEmpty()) ? Integer.parseInt(pageStr) : 1;
            int pageSize = (pageSizeStr != null && !pageSizeStr.isEmpty()) ? Integer.parseInt(pageSizeStr) : 10;

            int offset = (page - 1) * pageSize;

            // Recherche des utilisateurs et calcul du total
            List<User> results = userService.searchUsers(name, email, minScore, birthYear, offset, pageSize);
            int totalCount = userService.getTotalCount(name, email, minScore, birthYear);

            // Transmission des résultats et informations de pagination à la JSP
            request.setAttribute("results", results);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("hasPrevious", page > 1);
            request.setAttribute("hasNext", offset + results.size() < totalCount);

            // Gestion du message si aucun résultat trouvé
            if (results.isEmpty()) {
                request.setAttribute("message", "Aucun utilisateur trouvé pour les critères spécifiés.");
            }

            // Redirection vers la page de recherche
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Gestion des erreurs de format des paramètres
            request.setAttribute("error", "Veuillez entrer des valeurs valides pour les critères de recherche.");
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur inattendue est survenue.");
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }
}
