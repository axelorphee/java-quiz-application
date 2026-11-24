package infr;

import model.Answer;
import model.Question;
import model.Test;
import model.User;
import service.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ContextManager {
    private final UserService userService = UserService.getInstance();
    private final TestService testService = TestService.getInstance();
    private final QuestionService questionService = QuestionService.getInstance();
    private final AnswerService answerService = AnswerService.getInstance();
    private final ResultService resultService = ResultService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void startQuiz() {
        User user = registerOrLoginUser();
        if (user == null) {
            System.out.println("Erreur lors de la création de l'utilisateur. Exiting...");
            return;
        }

        Test test = selectTest();
        if (test == null) {
            System.out.println("Aucun test disponible. Exiting...");
            return;
        }

        int score = conductQuiz(test);
        System.out.println("Votre score final est : " + score);

        // Enregistrement du résultat
        resultService.addResult(user.getId(), test.getId(), score);
    }

    private User registerOrLoginUser() {
        System.out.println("Bienvenue ! Êtes-vous un nouvel utilisateur ? (y/n)");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("y")) {
            return createUser();
        } else {
            System.out.print("Entrez votre ID utilisateur : ");
            try {
                int userId = Integer.parseInt(scanner.nextLine().trim());
                User user = userService.getUserById(userId);
                if (user == null) {
                    System.out.println("Utilisateur introuvable. Veuillez réessayer.");
                }
                return user;
            } catch (NumberFormatException e) {
                System.out.println("ID utilisateur invalide. Veuillez entrer un nombre.");
                return null;
            }
        }
    }

    private User createUser() {
        try {
            System.out.print("Entrez votre nom : ");
            String nom = scanner.nextLine().trim();
            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine().trim();
            System.out.print("Entrez votre date de naissance (AAAA-MM-JJ) : ");
            String dob = scanner.nextLine().trim();

            Date dateOfBirth = Date.valueOf(dob);
            return userService.createUser(nom, email, dateOfBirth);
        } catch (IllegalArgumentException e) {
            System.out.println("Date de naissance invalide. Veuillez entrer une date au format AAAA-MM-JJ.");
            return null;
        }
    }

    private Test selectTest() {
        List<Test> tests = testService.getAllTests();

        if (tests.isEmpty()) {
            System.out.println("Aucun test disponible.");
            return null;
        }

        System.out.println("\nSélectionnez un test parmi les options disponibles :");
        for (int i = 0; i < tests.size(); i++) {
            System.out.println((i + 1) + ". " + tests.get(i).getTheme());
        }

        System.out.print("Entrez le numéro du test choisi : ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= tests.size()) {
                return tests.get(choice - 1);
            } else {
                System.out.println("Numéro de test invalide.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            return null;
        }
    }

    private int conductQuiz(Test test) {
        int score = 0;
        List<Question> questions = questionService.getQuestionsByTestId(test.getId());

        if (questions.isEmpty()) {
            System.out.println("Aucune question disponible pour ce test.");
            return score;
        }

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            List<Answer> answers = answerService.getAnswersByQuestionId(question.getId());

            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getAnswerText());
            }

            System.out.print("Entrez le numéro de votre réponse : ");
            try {
                int userChoice = Integer.parseInt(scanner.nextLine().trim());
                if (userChoice > 0 && userChoice <= answers.size()) {
                    if (answers.get(userChoice - 1).isCorrect()) {
                        System.out.println("Bonne réponse !");
                        score += 5; // Points pour une bonne réponse
                    } else {
                        System.out.println("Mauvaise réponse.");
                    }
                } else {
                    System.out.println("Numéro de réponse invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
        return score;
    }
}
