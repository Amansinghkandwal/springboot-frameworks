package org.example;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try{
            UserClassHibernate user = new UserClassHibernate("Aman");
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            System.out.println("User save with id:"+user.getId());
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            HibernateUtil.close();
        }

    }
}