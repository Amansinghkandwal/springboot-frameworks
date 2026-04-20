package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager=JPAUtil.getEntityManager();
        try{
            UserClassHibernate user = new UserClassHibernate("Aman");
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("User save with id:"+user.getId());
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            entityManager.close();
            JPAUtil.close();
        }

    }
}

// JPA --> Specification / Contract
// Implementation --> Hibernate , EclipseLink , OpenJPA