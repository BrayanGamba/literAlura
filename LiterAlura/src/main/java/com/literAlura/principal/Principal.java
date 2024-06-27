package com.literAlura.principal;

import com.literAlura.model.Autor;
import com.literAlura.model.DatosLibro;
import com.literAlura.model.GutendexResponse;
import com.literAlura.model.Libro;
import com.literAlura.repository.AutorRepository;
import com.literAlura.repository.LibroRepository;
import com.literAlura.service.ConsumoAPI;
import com.literAlura.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                ============================================================
                Elija la opción a través de su número:
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos desde un año determinado
                5 - Listar libros por idioma
                
                0 - Salir
                """;
            System.out.println(menu);

            try {
                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEnUnYearDeterminado();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                entrada.nextLine();
            }
        }
    }


    private GutendexResponse getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = entrada.nextLine().trim();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        //System.out.println(json);
        GutendexResponse libro = conversor.obtenerDatos(json, GutendexResponse.class);
        return libro;
    }

    private void buscarLibro() {
        GutendexResponse libro = getDatosLibro();

        Optional<Libro> libroEncontrado = libro.results().stream()
                .map(Libro::new)
                .findFirst();

        if (libroEncontrado.isPresent()) {
            Libro unicoLibro = libroEncontrado.get();
            System.out.println(unicoLibro);

            Optional<Libro> libroExistente = libroRepository.findByTitulo(unicoLibro.getTitulo());
            if (libroExistente.isEmpty()) {
                Autor unicoAutor = unicoLibro.getAutor();
                Optional<Autor> autorExistente = autorRepository.findByNombre(unicoAutor.getNombre());
                if (autorExistente.isPresent()) {
                    unicoLibro.setAutor(autorExistente.get());
                } else {
                    autorRepository.save(unicoAutor);
                }
                libroRepository.save(unicoLibro);
            }
        } else {
            System.out.println("No se encontró ningún libro.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        libros.stream().forEach(System.out::println);
    }


    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarAutoresVivosEnUnYearDeterminado() {
        System.out.println("Fecha en la que deseas buscar");
        try {
            var fecha = entrada.nextInt();
            entrada.nextLine();
            int yearStar = (fecha / 100) * 100;
            int yearEnd = yearStar + 100;
            List<Autor> autores = autorRepository.findAutoresVivosEnPeriodo(yearStar, yearEnd);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores en el año indicado.");
            } else {
                autores.stream().forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            entrada.nextLine();
        }
    }


    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros:" + "\n" +
                "es - español " + "\n" +
                "en - inglés" + "\n" +
                "fr - francés" + "\n" +
                "pt - portugués");

        String idioma = entrada.nextLine();
        List<String> idiomasValidos = Arrays.asList("es", "en", "fr", "pt");
        while (!idiomasValidos.contains(idioma)) {
            System.out.println("Por favor, ingresa uno de los idiomas válidos (es, en, fr, pt).");
            idioma = entrada.nextLine();
        }

        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}
