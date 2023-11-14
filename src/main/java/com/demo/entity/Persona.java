package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {

    private long id;

    private int dni;

    private String nombre;
    private String apellido;

    private LocalDate fechNac;
    private int edad;

    private String nacionalidad;

    private String email;
}
