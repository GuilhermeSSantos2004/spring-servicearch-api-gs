package com.guilhermessantos2004.servicearch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId;
    private String imagePath;
    private String name;
    private Integer status;      // 1 = vivo, 3 = morto
    private Integer identified;  // 0 = não identificado, 1 = identificado
    private String ncep;         // Novo campo: CEP onde foi encontrado (opcional)

    public Person() {}

    public Person(Long externalId, String imagePath, String name, Integer status, Integer identified, String ncep) {
        this.externalId = externalId;
        this.imagePath = imagePath;
        this.name = name;
        this.status = status;
        this.identified = identified;
        this.ncep = ncep;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getExternalId() { return externalId; }
    public void setExternalId(Long externalId) { this.externalId = externalId; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getIdentified() { return identified; }
    public void setIdentified(Integer identified) { this.identified = identified; }

    public String getNcep() { return ncep; }
    public void setNcep(String ncep) { this.ncep = ncep; }
}
