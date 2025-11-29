package com.example.colegio.controller;

import com.example.colegio.dto.MatriculaDto;
import com.example.colegio.dto.MatriculaRequestDto;
import com.example.colegio.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matricula")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    /**
     * Crear nueva matrícula
     * POST: http://localhost:8080/api/v1/matricula/save
     */
    @PostMapping("/save")
    public ResponseEntity<MatriculaDto> createMatricula(
            @Valid @RequestBody MatriculaRequestDto requestDTO) {

        MatriculaDto response = matriculaService.createMatricula(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Buscar matrículas de un estudiante
     * GET: http://localhost:8080/api/v1/matricula/estudiante/12345678
     */
    @GetMapping("/estudiante/{estudianteDni}")
    public ResponseEntity<List<MatriculaDto>> getMatriculasByEstudiante(
            @PathVariable String estudianteDni) {

        List<MatriculaDto> response = matriculaService.getMatriculasByEstudiante(estudianteDni);
        return ResponseEntity.ok(response);
    }

    /**
     * Buscar matrículas de un curso
     * GET: http://localhost:8080/api/v1/matricula/curso/1
     */
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<MatriculaDto>> getMatriculasByCurso(
            @PathVariable Long cursoId) {

        List<MatriculaDto> response = matriculaService.getMatriculasByCurso(cursoId);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener todas las matrículas
     * GET: http://localhost:8080/api/v1/matricula
     */
    @GetMapping
    public ResponseEntity<List<MatriculaDto>> getAllMatriculas() {
        List<MatriculaDto> response = matriculaService.getAllMatriculas();
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener matrícula por ID
     * GET: http://localhost:8080/api/v1/matricula/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> getMatriculaById(@PathVariable Long id) {
        MatriculaDto response = matriculaService.getMatriculaById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar nota de matrícula
     * PUT: http://localhost:8080/api/v1/matricula/1/nota?nota=85.5
     */
    @PutMapping("/{id}/nota")
    public ResponseEntity<MatriculaDto> updateNota(
            @PathVariable Long id,
            @RequestParam Double nota) {

        MatriculaDto response = matriculaService.updateNota(id, nota);
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar matrícula
     * DELETE: http://localhost:8080/api/v1/matricula/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        matriculaService.deleteMatricula(id);
        return ResponseEntity.noContent().build();
    }
}
