package org.barisd;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbladres")
public class Address {
    @Id
    private Long id;
    private String il;
    private String ilce;
    private String mahalle;

    public Address() {
    }

    public Address(Long id, String il, String ilce, String mahalle) {
        this.id = id;
        this.il = il;
        this.ilce = ilce;
        this.mahalle = mahalle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }
}

