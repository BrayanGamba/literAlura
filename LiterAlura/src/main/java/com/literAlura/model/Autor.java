package com.literAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int anioNacimiento;
    private int anioMuerte;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(String nombre, int anioNacimiento, int anioMuerte) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioMuerte = anioMuerte;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------------------------------\n");
        stringBuilder.append("Autor: ").append(nombre).append("\n");
        stringBuilder.append("Fecha de nacimiento: ").append(anioNacimiento).append("\n");
        stringBuilder.append("Fecha de fallecimiento: ").append(anioMuerte).append("\n");
        stringBuilder.append("Libros escritos:\n");

        if (libros != null) {
            for (Libro libro : libros) {
                stringBuilder.append("- ").append(libro.getTitulo()).append("\n");
            }
        } else {
            stringBuilder.append("No se han registrado libros.\n");
        }

        stringBuilder.append("-------------------------------------");
        return stringBuilder.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(int anioMuerte) {
        this.anioMuerte = anioMuerte;
    }
}
