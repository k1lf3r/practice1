// Yelgazy Dias , IT-#2505 , Gym Proejct
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Gym Management System ===" +
                "\n You've entered the wrong dungeon, my little slave. Prepare for the boy next door"+"\n");

        // 1. Create objects
        Member m1 = new Member(67, "Dias", 22, "Basic");
        Member m2 = new Member(52, "John Pork", 25, "Basic");

        Trainer t1 = new Trainer(101, "Mik", "Power", 7);
        Trainer t2 = new Trainer(102, "Bob-shbob", "Cardio", 2);

        WorkoutSession s1 = new WorkoutSession(500, "Dias", "Mik", 65); // Jestkiy jim leja 200kg
        WorkoutSession s2 = new WorkoutSession(501, "John Pork", "Bob-shbob", 40); // Cardio trenka - dlya loxov

        // 2. Display initial state
        System.out.println("--- Initial Objects ---");
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(s1);

        // 3. Test Getters
        System.out.println("\n--- Testing Getters ---");
        System.out.println("Member Name: " + m1.getName());
        System.out.println("Trainer Exp: " + t1.getExperience());
        System.out.println("Session Duration: " + s1.getDuration());

        // 4. Test Setters
        System.out.println("\n--- Testing Setters ---");
        m2.setName("John Updated");
        m2.setAge(26);
        System.out.println("Updated m2: " + m2);

        // 5. Test Additional Method
        System.out.println("\n--- Testing Logic Methods ---");

        // Member methods
        m1.upgrade();
        System.out.println("Is m1 active? " + m1.isActive());

        // Trainer methods
        System.out.println("Is Mik experienced? " + t1.isExperienced());
        System.out.println("Can Bob teach Cardio? " + t2.canTeach("Cardio"));

        // Session methods
        s1.extend(2);
        System.out.println("Is s2 complete? " + s2.complete());

        // 6. Display final state
        System.out.println("\n--- Final Objects ---");
        System.out.println(m1);
        System.out.println(s1);

        System.out.println("\n=== Program Complete ===");
    }
}