package com.example.colegio.service;

import com.example.colegio.dto.CursoDto;
import com.example.colegio.dto.EstudianteDto;
import com.example.colegio.entity.CursoEntity;
import com.example.colegio.entity.EstudianteEntity;
import com.example.colegio.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;

    public CursoService(CursoRepository cursoRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.modelMapper = modelMapper;
    }

    public List<CursoEntity> ListarTodosCursos(){
        return cursoRepository.findAll();
    }

    // Obtener curso por ID con estudiantes matriculados
    public CursoDto getCursoById(Long id) {
        CursoEntity cursoEntity = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return modelMapper.map(cursoEntity, CursoDto.class);
    }

    // crear estudiante
    public CursoDto crearCurso(CursoDto cursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setCodigo(cursoDto.getCodigo());
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setProfesor(cursoDto.getProfesor());
        cursoEntity.setCreditos(cursoDto.getCreditos());
        cursoRepository.save(cursoEntity);
        var cursoResponseDto = modelMapper.map(cursoEntity, CursoDto.class);
        return cursoResponseDto;
    }


    /*public CursoEntity actualizarCurso(Long id, CursoEntity cursoEntity) {
        return cursoRepository.findById(id)
                .map(aerolinea -> {
                    aerolinea.setCodigo(cursoEntity.getCodigo());
                    aerolinea.setNombre(cursoEntity.getNombre());
                    aerolinea.setProfesor(cursoEntity.getProfesor());
                    aerolinea.setCreditos(cursoEntity.getCreditos());
                    return cursoRepository.save(aerolinea);
                })
                .orElse(null);
    }*/
}
