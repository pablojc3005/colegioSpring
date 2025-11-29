package com.example.colegio.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CursoMatriculadoDto {
    private Long cursoId;
    private String codigoCurso;
    private String nombreCurso;
    private String profesor;
    private Date fechaInscripcion;
    private Double nota;

    public CursoMatriculadoDto(/*Long cursoId,*/ String codigoCurso, String nombreCurso,
                               /*String profesor, Date fechaInscripcion,*/ Double nota) {
        //this.cursoId = cursoId;
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        /*this.profesor = profesor;
        this.fechaInscripcion = fechaInscripcion;*/
        this.nota = nota;
    }
}
