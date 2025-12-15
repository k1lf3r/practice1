public class WorkoutSession {

    // Private fields
    private int sessionId;
    private String memberName;
    private String trainerName;
    private int duration;

    // Constructor with parameters
    public WorkoutSession(int sessionId, String memberName, String trainerName, int duration) {
        this.sessionId = sessionId;
        this.memberName = memberName;
        this.trainerName = trainerName;
        this.duration = duration;
    }

    // Default constructor
    public WorkoutSession() {
        this.sessionId = 0;
        this.memberName = "Unknown";
        this.trainerName = "Unknown";
        this.duration = 0;
    }

    // Getters
    public int getSessionId() {
        return sessionId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getDuration() {
        return duration;
    }

    // Setters
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Additional methods
    public void extend(int extraMinutes) {
        this.duration += extraMinutes;
    }

    public boolean complete() {
        return duration > 0;
    }

    // toString
    @Override
    public String toString() {
        return "WorkoutSession [id=" + sessionId + ", member=" + memberName +
                ", trainer=" + trainerName + ", duration=" + duration + "]";
    }
}
