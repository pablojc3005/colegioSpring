package com.example.colegio.controller;

import com.example.colegio.dto.EstudianteDto;
import com.example.colegio.entity.CursoEntity;
import com.example.colegio.entity.EstudianteEntity;
import com.example.colegio.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Listar Todos Los Cursos
    @GetMapping
    public List<EstudianteEntity> ListarTodosEstudiantes() {
        return estudianteService.ListarTodosEstudiantes();
    }

    // Listar Curso por ID
    @GetMapping("/{id}")
    public Optional<EstudianteEntity> ListarEstudiantePorId(Long id) {
        return estudianteService.ListarEstudiantePorId(id);
    }

    @GetMapping("/get/{dni}")
    public ResponseEntity<EstudianteDto> getEstudiante(@PathVariable String dni) {
        EstudianteDto response = estudianteService.BuscarEstudianteDNI(dni);
        return ResponseEntity.ok(response);
    }

    // Crear Curso
    @PostMapping("/crear")
    public ResponseEntity<EstudianteDto> CrearEstudiante(@RequestBody EstudianteDto estudianteDto) {
        EstudianteDto estudianteDto1 = estudianteService.crearEstudiante(estudianteDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(estudianteDto1);
    }

    // Actualizar Curso
    /*@PutMapping("/{id}")
    public ResponseEntity<EstudianteDto> actualizarCurso(@PathVariable Long id, @RequestBody EstudianteDto estudianteDto) {
        var cursoActualizado = estudianteService.ac(id, estudianteDto);
        if (cursoActualizado != null) {
            return ResponseEntity.ok(cursoActualizado);
        }
        return ResponseEntity.notFound().build();
    }*/
}
