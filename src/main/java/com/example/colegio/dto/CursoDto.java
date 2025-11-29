package com.example.colegio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CursoDto {

    private int id;
    private String codigo;
    private String nombre;
    private String profesor;
    private int creditos;
    private List<EstudianteMatriculadoDto> estudiantesMatriculados = new ArrayList<>();

    public CursoDto(int id, String codigo, String nombre, String profesor, int creditos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.creditos = creditos;
    }
}
