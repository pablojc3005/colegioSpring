package com.example.colegio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDto {
    private Long id;
    private String estudiante;
    private String curso;
    private LocalDate fechaInscripcion;
    private Double nota;

}
