//Yelgazy Dias , IT-#2505 , Gym Proejct
public class Main {

    public static void main(String[] args) {

        // Welcome message
        System.out.println("=== Gym Management System ===\n");

        // Create members
        Member m1 = new Member(1, "Dias", 22, "Basic");
        Member m2 = new Member(2, "John Pork", 25, "Basic");

        // Create trainers
        Trainer t1 = new Trainer(101, "Mike", "Power", 7);
        Trainer t2 = new Trainer(102, "Bob", "Cardio", 2);

        // Create workout sessions
        WorkoutSession s1 = new WorkoutSession(
                500,
                "Dias",
                "Mike",
                90
        ); // Jestkiy jim leja 200kg - for hardcore lifters

        WorkoutSession s2 = new WorkoutSession(
                501,
                "John Pork",
                "Bob",
                40
        ); // Cardio trenka - dlya loxov

        // Display initial state
        System.out.println("--- Initial Objects ---");
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(s1);
        System.out.println(s2);

        // Test methods
        System.out.println("\n--- Methods ---");
        m1.upgrade();
        s1.extend(10);

        // Final state
        System.out.println("\n--- Final Objects ---");
        System.out.println(m1);
        System.out.println(s1);

        // Completion message
        System.out.println("\n=== Program Complete ===");
    }
}