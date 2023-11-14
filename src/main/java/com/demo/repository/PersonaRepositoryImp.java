package com.demo.repository;

import com.demo.entity.Persona;
import com.demo.exception.PersonaAlreadyExistException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class PersonaRepositoryImp implements IPersonaRepository{

    @Override
    public List<Persona> findAll() {
        return dataBase;
    }

    @Override
    public Persona findById(long id) {
        return dataBase.stream().filter(persona -> persona.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Persona findByDni(long dni) {
        return dataBase.stream().filter(persona -> persona.getDni() == dni).findFirst().orElse(null);
    }

    @Override
    public List<Persona> findByAge(Integer min, Integer max) {
        List<Persona> lista = new ArrayList<>();
        if(min!=null && max!=null) {
            lista = dataBase.stream().filter(p -> p.getEdad() >= min && p.getEdad() <= max).toList();
        }else if(min!=null){
            lista = dataBase.stream().filter(p -> p.getEdad() >= min).toList();
        }else if(max!=null){
            lista = dataBase.stream().filter(p -> p.getEdad() <= max).toList();
        }else{
            lista = dataBase;
        }

        //lista.sort(Comparator.comparingInt(Persona::getEdad));

        return lista;
    }

    @Override
    public Persona save(Persona persona) {
        persona.setId(id);
        id++;
        dataBase.add(persona);
        return findById(persona.getId());
    }

    @Override
    public Persona edit(Persona persona) {
        for (Persona p : dataBase) {
            if(p.getId() == persona.getId()){
                p.setDni(persona.getDni());
                p.setNombre(persona.getNombre());
                p.setApellido(persona.getApellido());
                p.setEdad(persona.getEdad());
                p.setNacionalidad(persona.getNacionalidad());
                return p;
            }
        }
        return  null;
    }

    @Override
    public Boolean Delete(long id) {
        for (Persona p : dataBase) {
            if(p.getId() == id){
                dataBase.remove(p);
                return true;
            }
        }
        return false;
    }

    //--Datos HardCodeados----------------------------------------------------------------------------------------
    private List<Persona> dataBase = new ArrayList<>();
    private long id = 11;

    public PersonaRepositoryImp(){

        dataBase.add(new Persona(1,12345678,"Juan","Perez", LocalDate.of(2000, 12, 1),22,"Argentina","juanPerez@gmail.com"));
        dataBase.add(new Persona(2,23456789,"Luis","Hernandez", LocalDate.of(1985, 12, 1),35,"Paraguaya","luishernandez@gmail.com"));
        dataBase.add(new Persona(3,34567890,"Maria","Suarez", LocalDate.of(1990, 12, 1),23,"Boliviana","mariaSuarez@gmail.com"));
        dataBase.add(new Persona(4,56789012,"Jose","Lopez", LocalDate.of(2015, 12, 1),52,"Argentina","joselopez@gmail.com"));
        dataBase.add(new Persona(5,67890123,"Ernesto","Dominguez", LocalDate.of(1993, 12, 1),12,"Uruguaya","hernestodominguez@gmail.com"));
        dataBase.add(new Persona(6,78901234,"Hernan","Peralta", LocalDate.of(2005, 12, 1),36,"Argentina","hernanperalta@gmail.com"));
        dataBase.add(new Persona(7,89012345,"Julia","Gomez", LocalDate.of(1983, 12, 1),18,"Venezolana","juliagomez@gmail.com"));
        dataBase.add(new Persona(8,90123456,"Ramona","Rosales", LocalDate.of(1999, 12, 1),75,"Argentina","ramonarosales@gmail.com"));
        dataBase.add(new Persona(9,11111111,"Pedro","Ameguino", LocalDate.of(2020, 12, 1),16,"Venezolana","pedroameguino@gmail.com"));
        dataBase.add(new Persona(10,22222222,"Paco","Sarmiento", LocalDate.of(2000, 12, 1),29,"Argentina","pacosarmiento@gmail.com"));
    }
    //------------------------------------------------------------------------------------------------------------
}
