package com.example.colegio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class EstudianteEntity {
    @Id
    @Column(name = "dni")
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechanacimiento;

    // Relación uno a muchos con Matricula
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<MatriculaEntity> matriculas;
}
