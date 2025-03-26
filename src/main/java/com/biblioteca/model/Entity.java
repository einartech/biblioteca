package com.biblioteca.model;

public class Entity {

    // PROPIEDADES PRIVADAS DE LA CLASE - ENTIDAD
    private int id;
    private String name;
    private String surname;
    private int age;
    private boolean exist;

    // CONSTRUCTOR DEL OBJETO
    public Entity(int id, String name, String surname, int age, boolean exist) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.exist = exist;
    }

    // GETTERS
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public boolean getExist() {
        return this.exist;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
