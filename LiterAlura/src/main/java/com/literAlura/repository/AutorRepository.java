package com.literAlura.repository;

import com.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :yearEnd AND a.anioMuerte >= :yearStart")
    List<Autor> findAutoresVivosEnPeriodo(@Param("yearStart") int yearStart, @Param("yearEnd") int yearEnd);

}
