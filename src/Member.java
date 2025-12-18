class Member {
    // 1. PRIVATE FIELDS
    private int memberId;
    private String name;
    private int age;
    private String membershipType;

    // 2. CONSTRUCTOR WITH PARAMETERS
    public Member(int memberId, String name, int age, String membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
    }

    // 4. GETTERS
    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMembershipType() { return membershipType; }

    // 5. SETTERS
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    // 6. ADDITIONAL METHOD
    public boolean isActive() {
        return age > 0;
    }

    public void upgrade() {
        this.membershipType = "Premium";
        System.out.println("Member upgraded to Premium!");
    }

    // 7. toString() METHOD
    @Override
    public String toString() {
        return "Member [id=" + memberId + ", name=" + name + ", age=" + age + ", type=" + membershipType + "]";
    }
}