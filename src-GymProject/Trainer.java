class Trainer {
    // 1. PRIVATE FIELDS
    private int trainerId;
    private String name;
    private String specialization;
    private int experience;

    // 2. CONSTRUCTOR
    public Trainer(int trainerId, String name, String specialization, int experience) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    // 4. GETTERS
    public int getTrainerId() { return trainerId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }

    // 5. SETTERS
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setExperience(int experience) { this.experience = experience; }

    // 6. ADDITIONAL METHOD
    public boolean isExperienced() {
        return experience >= 5;
    }

    public boolean canTeach(String type) {
        return specialization.equals(type);
    }

    // 7. toString()
    @Override
    public String toString() {
        return "Trainer [id=" + trainerId + ", name=" + name + ", spec=" + specialization + ", exp=" + experience + "]";
    }
}