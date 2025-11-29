package com.example.colegio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDto {
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    private List<CursoMatriculadoDto> cursosMatriculados = new ArrayList<>();

    public EstudianteDto(String dni, String nombre, String apellido,
                                 String email, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }
}
