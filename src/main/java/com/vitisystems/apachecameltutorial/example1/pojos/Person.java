package com.vitisystems.apachecameltutorial.example1.pojos;

import java.sql.Date;

public class Person {
    private String nombre;
    private Integer edad;
    private Date fecha;

    public Person(String nombre, Integer edad, Date fecha) {
        this.nombre = nombre;
        this.edad = edad;
        this.fecha = fecha;
    }   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }    
}
