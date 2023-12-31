package com.barisd.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_personel")
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer age;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "d_id")
    Department department;

    @Override
    public String toString() {
        return "Personel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department.getName() +
                '}';
    }
}
