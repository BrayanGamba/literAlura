package com.literAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_autor", referencedColumnName = "nombre")
    private Autor autor;
    private List<String> idioma;
    private int descargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        DatosAutor datosAutor = datosLibro.autor().get(0);
        this.autor = new Autor(datosAutor.nombre(), datosAutor.anioNacimiento(), datosAutor.anioMuerte());
        this.idioma = datosLibro.idioma();
        this.descargas = datosLibro.descargas();
    }

    @Override
    public String toString() {
        return  "--------------LIBROS----------------" + "\n" +
                "Título: " + titulo + "\n" +
                "Autores: " + autor.getNombre() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + descargas + "\n" +
                "------------------------------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }
}
