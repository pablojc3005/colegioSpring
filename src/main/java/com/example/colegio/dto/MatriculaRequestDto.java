package com.example.colegio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatriculaRequestDto {

    @NotNull(message = "El DNI del estudiante es obligatorio")
    private String estudianteDni;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long cursoId;
}
