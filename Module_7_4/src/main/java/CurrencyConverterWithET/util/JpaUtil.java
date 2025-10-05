package CurrencyConverterWithET.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT_NAME = "CurrencyConverterPU";
    private static EntityManagerFactory entityManagerFactory;

    private JpaUtil() {
        // Private constructor to prevent instantiation
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                System.out.println("EntityManagerFactory created successfully.");
            } catch (Exception e) {
                System.err.println("Failed to create EntityManagerFactory: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Could not initialize JPA", e);
            }
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory closed.");
        }
    }

    public static boolean testConnection() {
        try {
            EntityManager em = getEntityManager();
            em.close();
            return true;
        } catch (Exception e) {
            System.err.println("JPA connection test failed: " + e.getMessage());
            return false;
        }
    }
}
