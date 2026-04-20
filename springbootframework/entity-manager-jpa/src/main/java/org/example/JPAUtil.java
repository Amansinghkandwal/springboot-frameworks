package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory entityMangerFactory;

    static {
        entityMangerFactory = Persistence.createEntityManagerFactory("myJpaUnit");
    }

    public static EntityManager getEntityManager()
    {
        return entityMangerFactory.createEntityManager();
    }

    public static void close()
    {
        entityMangerFactory.close();
    }

}
