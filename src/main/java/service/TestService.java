package service;

import model.Test;
import repository.TestRepository;

import java.util.List;

public class TestService {
    // Instance unique de la classe
    private static TestService instance;

    // Instance du repository
    private final TestRepository testRepository = TestRepository.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private TestService() {}

    // Méthode pour récupérer l'instance unique
    public static TestService getInstance() {
        if (instance == null) {
            synchronized (TestService.class) {
                if (instance == null) {
                    instance = new TestService();
                }
            }
        }
        return instance;
    }

    public Test createTest(String theme, int passingGrade) {
        Test test = new Test(theme, passingGrade);
        testRepository.addTest(test);
        return test;
    }

    public Test getTestById(int id) {
        return testRepository.getTestById(id);
    }

    public List<Test> getAllTests() {
        List<Test> tests = testRepository.getAllTests();

        // Diagnostic : Vérifie que les tests sont bien récupérés au niveau du service
        System.out.println("Tests disponibles (via TestService) :");
        for (Test test : tests) {
            System.out.println("ID=" + test.getId() + ", Thème=" + test.getTheme() + ", Note de passage=" + test.getPassingGrade());
        }
        return tests;
    }
}
