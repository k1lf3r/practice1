
public class Person {

    protected int id;
    protected String name;
    protected int age;

    public Person(int id, String name, int age) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");

        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getRole() {
        return "Person";
    }

    public String getInfo() {
        return id + " | " + name + " | age: " + age;
    }
}
