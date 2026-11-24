package service;

import model.User;
import repository.UserRepository;

import java.sql.Date;
import java.util.List;

public class UserService {
    // Instance unique de la classe
    private static UserService instance;

    // Instance du repository
    private final UserRepository userRepository = UserRepository.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private UserService() {}

    // Méthode pour récupérer l'instance unique
    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public User createUser(String nom, String email, Date dateOfBirth) {
        User user = new User(nom, email, dateOfBirth);
        userRepository.addUser(user);
        return user;
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public List<User> searchUsers(String name, String email, int minScore, int birthYear, int offset, int limit) {
        return userRepository.searchUsers(name, email, minScore, birthYear, offset, limit);
    }

    public int getTotalCount(String name, String email, int minScore, int birthYear) {
        return userRepository.getTotalCount(name, email, minScore, birthYear);
    }
}
