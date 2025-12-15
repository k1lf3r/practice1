public class Trainer {

    // Private fields
    private int trainerId;
    private String name;
    private String specialization;
    private int experience;

    // Constructor with parameters
    public Trainer(int trainerId, String name, String specialization, int experience) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    // Default constructor
    public Trainer() {
        this.trainerId = 0;
        this.name = "Unknown";
        this.specialization = "General";
        this.experience = 0;
    }

    // Getters
    public int getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    // Setters
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Additional methods
    public boolean isExperienced() {
        return experience >= 5;
    }

    public boolean canTeach(String type) {
        return specialization.equals(type);
    }

    // toString
    @Override
    public String toString() {
        return "Trainer [id=" + trainerId + ", name=" + name +
                ", specialization=" + specialization + ", experience=" + experience + "]";
    }
}
