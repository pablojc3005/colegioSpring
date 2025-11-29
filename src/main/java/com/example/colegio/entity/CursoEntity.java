package com.example.colegio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String codigo;
    private String nombre;
    private String profesor;
    private int creditos;

    // Relación uno a muchos con Matricula
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<MatriculaEntity> matriculas;
}
