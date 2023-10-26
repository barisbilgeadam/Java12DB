package com.barisd;
//Java12HibernateDB

import com.barisd.entity.Musteri;
import com.barisd.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Musteri m1=new Musteri("Ozan","Çelik","London");
        session.save(m1);

        transaction.commit();
        session.close();
    }
}