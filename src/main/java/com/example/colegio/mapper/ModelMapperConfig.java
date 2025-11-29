package com.example.colegio.mapper;
import com.example.colegio.dto.CursoDto;
import com.example.colegio.dto.EstudianteDto;
import com.example.colegio.entity.CursoEntity;
import com.example.colegio.entity.EstudianteEntity;
import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(EstudianteEntity.class, EstudianteDto.class)
                .addMapping(EstudianteEntity::getDni, EstudianteDto::setDni)
                .addMapping(EstudianteEntity::getNombre, EstudianteDto::setNombre)
                .addMapping(EstudianteEntity::getApellido, EstudianteDto::setApellido)
                .addMapping(EstudianteEntity::getEmail, EstudianteDto::setEmail)
                .addMapping(EstudianteEntity::getFechanacimiento, EstudianteDto::setFechaNacimiento);

        modelMapper.createTypeMap(CursoEntity.class, CursoDto.class)
                .addMapping(CursoEntity::getCodigo, CursoDto::setCodigo)
                .addMapping(CursoEntity::getNombre, CursoDto::setNombre)
                .addMapping(CursoEntity::getProfesor, CursoDto::setProfesor)
                .addMapping(CursoEntity::getCreditos, CursoDto::setCreditos);



        /*modelMapper.createTypeMap(ArticuloEntity.class, ArticuloResponseDto.class)
                .addMapping(ArticuloEntity::getArticuloId, ArticuloResponseDto::setArticuloId)
                .addMapping(ArticuloEntity::getTitulo, ArticuloResponseDto::setTitulo)
                .addMapping(ArticuloEntity::getContenido, ArticuloResponseDto::setContenido)
                .addMapping(ArticuloEntity::getFechaCreacion, ArticuloResponseDto::setFechaCreacion)
                .addMapping(ArticuloEntity::getFechaActualizacion, ArticuloResponseDto::setFechaActualizacion)
                .addMapping(ArticuloEntity::getUrlArticulo, ArticuloResponseDto::setUrlArticulo);*/

        return modelMapper;
    }
}
