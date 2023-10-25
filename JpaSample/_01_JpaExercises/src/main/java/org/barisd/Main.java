package org.barisd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Address address = new Address();
        address.setId(1L);
        address.setIl("123 Main St");
        address.setIlce("Anytown");
        address.setMahalle("12345");

        addAddress(address);
    }

    public static void addAddress(Address address) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PGJava12SatisVT_PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(address); // Yeni adresi ekle
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Hata! => ");e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}