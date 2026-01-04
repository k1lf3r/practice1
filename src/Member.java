
public class Member extends Person {

    private String membershipType;

    public Member(int id, String name, int age, String membershipType) {
        super(id, name, age);
        this.membershipType = membershipType;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public String getInfo() {
        return "[MEMBER] " + super.getInfo() + " | type: " + membershipType;
    }

    public void upgrade() {
        membershipType = "Premium";
    }
}
