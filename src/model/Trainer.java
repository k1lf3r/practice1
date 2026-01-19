package model;

import exception.InvalidDataException;

public class Trainer extends Person {

    private String specialization;
    private int experience;

    public Trainer(int id, String name, int age, String specialization, int experience) {
        super(id, name, age);
        setSpecialization(specialization);
        setExperience(experience);
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty())
            throw new InvalidDataException("Specialization cannot be empty.");
        this.specialization = specialization.trim();
    }

    public void setExperience(int experience) {
        if (experience < 0) throw new InvalidDataException("Experience cannot be negative.");
        this.experience = experience;
    }

    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }

    @Override
    public String getRole() {
        return "Trainer";
    }

    @Override
    public String getInfo() {
        return "[TRAINER] " + id + " | " + name + " | age: " + age
                + " | spec: " + specialization + " | exp: " + experience;
    }

    public boolean isExperienced() {
        return experience >= 5;
    }
}
