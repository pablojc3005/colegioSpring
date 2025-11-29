package com.example.colegio.service;

import com.example.colegio.dto.MatriculaDto;
import com.example.colegio.dto.MatriculaRequestDto;
import com.example.colegio.entity.CursoEntity;
import com.example.colegio.entity.EstudianteEntity;
import com.example.colegio.entity.MatriculaEntity;
import com.example.colegio.repository.CursoRepository;
import com.example.colegio.repository.EstudianteRepository;
import com.example.colegio.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    // Crear matrícula
    public MatriculaDto createMatricula(MatriculaRequestDto requestDTO) {
        // Verificar si el estudiante existe
        EstudianteEntity estudiante = estudianteRepository.findById(Long.valueOf(requestDTO.getEstudianteDni()))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con DNI: " + requestDTO.getEstudianteDni()));

        // Verificar si el curso existe
        CursoEntity curso = cursoRepository.findById(requestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + requestDTO.getCursoId()));

        // Verificar si ya existe la matrícula
        if (matriculaRepository.findByEstudianteDniAndCursoId(
                requestDTO.getEstudianteDni(), requestDTO.getCursoId()).isPresent()) {
            throw new RuntimeException("El estudiante ya está matriculado en este curso");
        }

        // Crear matrícula
        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setEstudiante(estudiante);
        matricula.setCurso(curso);
        matricula.setFechainscripcion(LocalDate.now());
        matricula.setNota(null); // Nota inicialmente null

        MatriculaEntity savedMatricula = matriculaRepository.save(matricula);
        return convertToResponseDTO(savedMatricula);
    }

    // Obtener matrículas por estudiante
    public List<MatriculaDto> getMatriculasByEstudiante(String estudianteDni) {
        // Verificar si el estudiante existe
        if (!estudianteRepository.existsById(Long.valueOf(estudianteDni))) {
            throw new RuntimeException("Estudiante no encontrado con DNI: " + estudianteDni);
        }

        List<MatriculaEntity> matriculas = matriculaRepository.findByEstudianteDni(estudianteDni);
        return matriculas.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Obtener matrículas por curso
    public List<MatriculaDto> getMatriculasByCurso(Long cursoId) {
        // Verificar si el curso existe
        if (!cursoRepository.existsById(cursoId)) {
            throw new RuntimeException("Curso no encontrado con ID: " + cursoId);
        }

        List<MatriculaEntity> matriculas = matriculaRepository.findByCursoId(cursoId);
        return matriculas.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Obtener todas las matrículas
    public List<MatriculaDto> getAllMatriculas() {
        return matriculaRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Actualizar nota de matrícula
    public MatriculaDto updateNota(Long matriculaId, Double nota) {
        MatriculaEntity matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + matriculaId));

        // Validar nota
        if (nota != null && (nota < 0 || nota > 100)) {
            throw new RuntimeException("La nota debe estar entre 0 y 100");
        }

        matricula.setNota(nota);
        MatriculaEntity updatedMatricula = matriculaRepository.save(matricula);
        return convertToResponseDTO(updatedMatricula);
    }

    // Eliminar matrícula
    public void deleteMatricula(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("Matrícula no encontrada con ID: " + id);
        }
        matriculaRepository.deleteById(id);
    }

    // Obtener matrícula por ID
    public MatriculaDto getMatriculaById(Long id) {
        MatriculaEntity matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
        return convertToResponseDTO(matricula);
    }

    // ========== MÉTODOS DE CONVERSIÓN MANUAL ==========

    // Convertir Entity a ResponseDTO
    private MatriculaDto convertToResponseDTO(MatriculaEntity matricula) {
        if (matricula == null) {
            return null;
        }

        MatriculaDto responseDTO = new MatriculaDto();
        responseDTO.setId(Long.valueOf(matricula.getId()));

        // Formatear nombre del estudiante
        if (matricula.getEstudiante() != null) {
            String nombreEstudiante = matricula.getEstudiante().getNombre() + " " +
                    matricula.getEstudiante().getApellido();
            responseDTO.setEstudiante(nombreEstudiante);
        }

        // Formatear nombre del curso
        if (matricula.getCurso() != null) {
            responseDTO.setCurso(matricula.getCurso().getNombre());
        }

        responseDTO.setFechaInscripcion(matricula.getFechainscripcion());
        responseDTO.setNota(matricula.getNota());

        return responseDTO;
    }
}
