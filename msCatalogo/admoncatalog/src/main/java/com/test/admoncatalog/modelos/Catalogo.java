package com.test.admoncatalog.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Catalogo{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreDocto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDocto() {
        return nombreDocto;
    }

    public void setNombreDocto(String nombreDocto) {
        this.nombreDocto = nombreDocto;
    }
}
