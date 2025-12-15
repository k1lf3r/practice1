public class Member {

    // Private fields
    private int memberId;
    private String name;
    private int age;
    private String membershipType;

    // Constructor with parameters
    public Member(int memberId, String name, int age, String membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
    }

    // Default constructor
    public Member() {
        this.memberId = 0;
        this.name = "Unknown";
        this.age = 0;
        this.membershipType = "Basic";
    }

    // Getters
    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    // Setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    // Additional methods
    public boolean isActive() {
        return age > 0;
    }

    public void upgrade() {
        this.membershipType = "Premium";
    }

    // toString
    @Override
    public String toString() {
        return "Member [id=" + memberId + ", name=" + name + ", age=" + age +
                ", membership=" + membershipType + "]";
    }
}
