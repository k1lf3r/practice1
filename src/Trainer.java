
public class Trainer extends Person {

    private String specialization;
    private int experience;

    public Trainer(int id, String name, int age, String specialization, int experience) {
        super(id, name, age);
        if (experience < 0) throw new IllegalArgumentException("Experience cannot be negative");

        this.specialization = specialization;
        this.experience = experience;
    }

    @Override
    public String getRole() {
        return "Trainer";
    }

    @Override
    public String getInfo() {
        return "[TRAINER] " + super.getInfo()
                + " | spec: " + specialization
                + " | exp: " + experience;
    }

    public boolean isExperienced() {
        return experience >= 5;
    }
}
