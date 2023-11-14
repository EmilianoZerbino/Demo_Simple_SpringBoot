package com.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaRequestWithIdDto {

    @NotNull(message = "El campo ID no debe ser nulo")
    private long id;

    @NotNull(message = "El campo DNI no debe ser nulo")
    @Min(value=5000000,message = "Debe ingresar un valor mayor a $5000000")
    @Max(value=99999999,message = "Debe ingresar un valor menor a $99999999")
    private int dni;

    @NotNull(message = "El campo nombre no debe ser nulo")
    @NotEmpty(message = "El campo nombre no puede quedar vacio.")
    @Size(min=3, max=24, message = "El nombre debe contener entre 3 y 24 caracteres.")
    private String nombre;

    @NotNull(message = "El campo apellido no debe ser nulo")
    @NotEmpty(message = "El campo apellido no puede quedar vacio.")
    @Size(min=3, max=24, message = "El apellido debe contener entre 3 y 24 caracteres.")
    private String apellido;

    @PastOrPresent(message = "La fecha de nacimiento debe ser igual o anterior al dia de hoy.")
    private LocalDate fechNac;

    @NotNull(message = "El campo edad no debe ser nulo")
    @PositiveOrZero(message = "La edad debe ser un valor mayor que 0.")
    @Max(value=130,message = "El programa registra edades de hasta 130 años")
    private int edad;

    @NotNull(message = "El campo nacionalidad no debe ser nulo")
    @Size(min=3, max=24, message = "La nacionalidad debe contener entre 3 y 24 caracteres.")
    private String nacionalidad;

    @NotNull(message = "El campo email no debe ser nulo")
    @Email(message = "El e-mail ingresado no es valido.")
    private String email;

}
