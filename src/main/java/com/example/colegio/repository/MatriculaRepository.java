package com.example.colegio.repository;

import com.example.colegio.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {

    List<MatriculaEntity> findByEstudianteDni(String estudianteDni);
    List<MatriculaEntity> findByCursoId(Long cursoId);
    Optional<MatriculaEntity> findByEstudianteDniAndCursoId(String estudianteDni, Long cursoId);

    // Consulta personalizada para cargar relaciones
    /*@Query("SELECT m FROM Matricula m LEFT JOIN FETCH m.estudiante LEFT JOIN FETCH m.curso WHERE m.estudiante.dni = :estudianteDni")
    List<MatriculaEntity> findByEstudianteDniWithDetails(@Param("estudianteDni") String estudianteDni);*/
}
