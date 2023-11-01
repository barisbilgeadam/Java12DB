package com.barisd.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "tblurun")
@NamedQueries({
        @NamedQuery(name = "Urun.findAll", query = "SELECT u FROM Urun u"),
        @NamedQuery(name = "Urun.findByAd", query="SELECT u FROM Urun u WHERE u.ad LIKE :urunad")
})
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    String barkod;
    String marka;
    String model;
    BigDecimal fiyat;
    Integer stok;
    @Embedded
    BaseEntity baseEntity;



}
