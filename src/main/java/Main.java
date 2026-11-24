import model.Test;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();

            int port = 8080; // Change si nécessaire
            tomcat.setPort(port);
            tomcat.getConnector().setProperty("address", "localhost");


            String webappDirLocation = "src/main/webapp/";
            tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

            // Logs pour confirmer la configuration
            System.out.println("Tomcat configuré sur le port : " + port);
            System.out.println("Chemin du webapp : " + new File(webappDirLocation).getAbsolutePath());

            tomcat.start();
            System.out.println("Tomcat démarré avec succès !");

            System.out.println();
            tomcat.getServer().await(); // Attente des requêtes

        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
