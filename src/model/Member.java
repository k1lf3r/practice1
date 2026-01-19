package model;

import exception.InvalidDataException;

public class Member extends Person {

    private String membershipType;

    public Member(int id, String name, int age, String membershipType) {
        super(id, name, age);
        setMembershipType(membershipType);
    }

    public void setMembershipType(String membershipType) {
        if (membershipType == null || membershipType.trim().isEmpty())
            throw new InvalidDataException("Membership type cannot be empty.");
        this.membershipType = membershipType.trim();
    }

    public String getMembershipType() {
        return membershipType;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public String getInfo() {
        return "[MEMBER] " + id + " | " + name + " | age: " + age + " | type: " + membershipType;
    }

    public void upgrade() {
        this.membershipType = "Premium";
    }
}
