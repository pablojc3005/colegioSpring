package com.example.colegio.service;

import com.example.colegio.dto.EstudianteDto;
import com.example.colegio.entity.EstudianteEntity;
import com.example.colegio.repository.EstudianteRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final ModelMapper modelMapper;


    public EstudianteService(EstudianteRepository estudianteRepository, ModelMapper modelMapper) {
        this.estudianteRepository = estudianteRepository;
        this.modelMapper = modelMapper;
    }

    public List<EstudianteEntity> ListarTodosEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Optional<EstudianteEntity> ListarEstudiantePorId(Long id){
        return estudianteRepository.findById(id);
    }

    // Obtener estudiante por DNI
    public EstudianteDto BuscarEstudianteDNI(String dni) {
        EstudianteEntity entity = estudianteRepository.findById(Long.valueOf(dni))
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con DNI: " + dni));
        return modelMapper.map(entity, EstudianteDto.class);
    }

    // crear estudiante
    public EstudianteDto crearEstudiante(EstudianteDto estudianteDto){
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setDni(estudianteDto.getDni());
        estudianteEntity.setNombre(estudianteDto.getNombre());
        estudianteEntity.setApellido(estudianteDto.getApellido());
        estudianteEntity.setEmail(estudianteDto.getEmail());
        estudianteEntity.setFechanacimiento(estudianteDto.getFechaNacimiento());
        estudianteRepository.save(estudianteEntity);
        var articuloResponseDto = modelMapper.map(estudianteEntity, EstudianteDto.class);
        return articuloResponseDto;
    }

    /*public EstudianteEntity actualizarEstudiante(Long id, EstudianteDto estudianteDto) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setDni(estudianteEntity.getDni());
                    estudiante.setNombre(estudianteEntity.getNombre());
                    estudiante.setApellido(estudianteEntity.getApellido());
                    estudiante.setEmail(estudianteEntity.getEmail());
                    estudiante.setFechanacimiento(estudianteEntity.getFechanacimiento());
                    return estudianteRepository.save(estudiante);
                })
                .orElse(null);
    }*/
}
