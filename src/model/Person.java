package model;

import exception.InvalidDataException;

public abstract class Person implements Identifiable {

    protected int id;
    protected String name;
    protected int age;

    public Person(int id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
    }

    // setters with validation (throw custom exception)
    public void setId(int id) {
        if (id <= 0) throw new InvalidDataException("ID must be positive.");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidDataException("Name cannot be empty.");
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 0) throw new InvalidDataException("Age cannot be negative.");
        this.age = age;
    }

    // Identifiable
    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return name; }

    // abstract methods to be implemented by children
    public abstract String getRole();
    public abstract String getInfo();
}
