package com.example.colegio.controller;

import com.example.colegio.dto.CursoDto;
import com.example.colegio.dto.EstudianteDto;
import com.example.colegio.entity.CursoEntity;
import com.example.colegio.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Listar Todos Los Cursos
    @GetMapping("list")
    public List<CursoEntity> ListarTodosCursos() {
        return cursoService.ListarTodosCursos();
    }

    // Listar Curso por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<CursoDto> getCurso(@PathVariable Long id) {
        CursoDto response = cursoService.getCursoById(id);
        return ResponseEntity.ok(response);
    }

    // Crear Curso
    // Crear Curso
    @PostMapping("/crear")
    public ResponseEntity<CursoDto> CrearCurso(@RequestBody CursoDto cursoDto) {
        CursoDto cursoDto1 = cursoService.crearCurso(cursoDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(cursoDto1);
    }

    // Actualizar Curso
    /*@PutMapping("/{id}")
    public ResponseEntity<CursoEntity> actualizarCurso(@PathVariable Long id, @RequestBody CursoEntity cursoEntity) {
        var cursoActualizado = cursoService.actualizarCurso(id, cursoEntity);
        if (cursoActualizado != null) {
            return ResponseEntity.ok(cursoActualizado);
        }
        return ResponseEntity.notFound().build();
    }*/

}
