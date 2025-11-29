package com.example.colegio.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EstudianteMatriculadoDto {
    private String dni;
    private String nombreCompleo;
    private Double notas;

    public EstudianteMatriculadoDto(String dni, String nombreCompleo, Double notas) {
        this.dni = dni;
        this.nombreCompleo = nombreCompleo;
        this.notas = notas;
    }
}
