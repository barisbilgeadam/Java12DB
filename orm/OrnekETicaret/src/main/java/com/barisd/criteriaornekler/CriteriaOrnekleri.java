package com.barisd.criteriaornekler;

import com.barisd.repository.entity.Musteri;
import com.barisd.repository.entity.Urun;
import com.barisd.repository.views.VwUrun;
import com.barisd.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class CriteriaOrnekleri {
    Session session;
    CriteriaBuilder criteriaBuilder;

    public CriteriaOrnekleri() {
        session= HibernateUtility.getSessionFactory().openSession();
        criteriaBuilder= session.getCriteriaBuilder();
    }

    /*
    Sorgu 1: Select * from tblmusteri   Geriye ne döner->müşterilerden oluşan bir liste dönecek.
     */
    public List<Musteri> findAll(){
        /**
         * Kriter tanımlanırken kullanılacak sınıfın adını vererek işleme başlarız.
         * Bu sınıf .class olarak verilmelidir.
         * Reflection API sayesinde arka planda o class içindeki tüm fieldlar belirlenir.
         */
        CriteriaQuery<Musteri> criteriaQuery = criteriaBuilder.createQuery(Musteri.class);
        /*
        Sorgunun atılacağı tabloyu belirleyelim.
         */
        Root<Musteri> root = criteriaQuery.from(Musteri.class);
        /*
        Sorguda hangi alanlar seçilecek onu tanımlamalıyız. select içindeki root ifadesi * anlamına gelmektedir.
         */
        criteriaQuery.select(root);
        /*
        Sorgunuz hazır olduktan sonra bbunu çalıştırmalısınız.
         */
        List<Musteri> resultList = session.createQuery(criteriaQuery).getResultList();
        /*
        sonuçları dönelim:
         */
        return resultList;
    }

    /**
     * Sorgu 2. Select ad from tblmusteri
     * Geriye ne döner?
     * Stringlerden oluşan bir liste dönecek.
     */
    public List<String> selectOneColumn(){
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        /*
        Sorguda select ile seçilen alanların olduğu kısım.
        Şu an ad sütunu seçili hale getirildi.
        root içerisinde tüm kolonlar vardır ve get ile onların içinden seçim yapılabilir.
         */
        criteriaQuery.select(root.get("ad"));
        List<String> resultList = session.createQuery(criteriaQuery).getResultList();
        return  resultList;
    }

    /**
     * Sorgu 3: Select ad from tblurun where id=idparametre
     * Geriye ne döner?
     * String döner.
     */
    public String selectOneColumnById(Long idparametre){
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);

        /**
         * Predicate bir koşul oluşturma işlemidir.
         * Where sorgusu bir veya birden çok predicate bekler.
         * Koşulunuzu predicate olarak oluşturup where sorgusuna aktarmalısınız.
         * = yerine equal metodu kullanılır.
         * > greaterThan gibi metodlar kullanılır.
         */
        Predicate p1=criteriaBuilder.equal(root.get("id"),idparametre);
        /*
        2 işlem ard arda gelebilir.
         */
        criteriaQuery.select(root.get("ad")).where(p1);

        String singleResult = session.createQuery(criteriaQuery).getSingleResult();
        return singleResult;

    }

    /**
     * Sorgu 4: Selet * from tblurun where id=?
     * Geri dönüş tipi ne?
     * Urun!
     */
    public Urun findUrunById(Long idparametre){
        CriteriaQuery<Urun> criteriaQuery = criteriaBuilder.createQuery(Urun.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        Predicate p1=criteriaBuilder.equal(root.get("id"),idparametre);
        criteriaQuery.select(root).where(p1);

        Urun singleResult = session.createQuery(criteriaQuery).getSingleResult();
        return singleResult;
    }
    /**
     * Sorgu 5: Select id,ad,fiyat from tblurun
     * Geri dönüş tipi ne?
     * Object[] veya tuple kullanılabilir.
    */


    public List<Object[]> selectManyColumn(){
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        Path<Long> pathId=root.get("id");
        Path<String> pathAd=root.get("ad");
        Path<BigDecimal> pathFiyat=root.get("fiyat");
        //criteriaQuery.select(criteriaBuilder.array(pathId,pathAd,pathFiyat));
        // üsteki metodun alternatifi multiselect'tir.
        criteriaQuery.multiselect(pathId,pathAd,pathFiyat);
        List<Object[]> resultList = session.createQuery(criteriaQuery).getResultList();
        return resultList;
    }

    /**
     * Sorgu 6. Select * from tblurun where ad like '%?%' and fiyat>?
     * Geriye ne döner?
     * Urun
     */
    public List<Urun> findAllByNameAndFiyatGt(String urunAd,BigDecimal fiyat){
        CriteriaQuery<Urun> criteriaQuery = criteriaBuilder.createQuery(Urun.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);

        Predicate p1=criteriaBuilder.like(root.get("ad"),urunAd);  //ad like '%?%'
        Predicate p2=criteriaBuilder.greaterThan(root.get("fiyat"),fiyat);// fiyat>?
        Predicate sonKosul=criteriaBuilder.and(p1,p2); //ad like '%?%' and fiyat>?

        criteriaQuery.select(root).where(sonKosul);

        return session.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Native Query yazımı: SQL kodları ile JPA(Hibernate) üzerinden sorgulama yapabilirsiniz.
     *
     * Eğer direkt sorguyu yazıp bırakırsanız geriye Object dizisi döner.
     * Gidip tip belirtmek gerekli.
     */
    public List<Urun> findAllNativeQuery(){
        List<Urun> resultList = session.createNativeQuery("SELECT * FROM tblurun", Urun.class).getResultList();
        return resultList;
    }
    public List<VwUrun> findAllNativeQuery2(){
         List<VwUrun> resultList = session.createNativeQuery("SELECT id,ad FROM tblurun", VwUrun.class).getResultList();
        return resultList;
    }
    /**
     * Named Query
     * Kullanılacak dil: JPQL, HQL
     * Entity üzerine yazılır.
     * SQL  -> SELECT * FROM tblmusteri
     * JPQL -> SELECT m FROM Musteri m
     * HQL  -> FROM Musteri
     */
    public List<Urun> findAllNamedQuery(){
      return session.createNamedQuery("Urun.findAll", Urun.class).getResultList();
    }

    public List<Urun> findAllByAd(String urunAdi){
        Query<Urun> namedQuery = session.createNamedQuery("Urun.findByAd", Urun.class);
        namedQuery.setParameter("urunad",urunAdi);

        return namedQuery.getResultList();
    }
}
