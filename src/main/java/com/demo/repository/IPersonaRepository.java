package com.demo.repository;

import com.demo.entity.Persona;

import java.util.List;

public interface IPersonaRepository {

    public List<Persona> findAll();
    public Persona findById(long id);
    public Persona findByDni(long dni);
    public List<Persona> findByAge(Integer min, Integer max);
    public Persona save(Persona persona);
    public Persona edit(Persona persona);
    public Boolean Delete(long id);
}
