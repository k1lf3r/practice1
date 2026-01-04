import java.util.ArrayList;
import java.util.Scanner;

// Yelgazy Dias , IT-#2505 , Gym Proejct

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();

        // initial test data
        people.add(new Member(1, "Dias", 22, "Basic"));
        people.add(new Trainer(2, "Mik", 30, "Power", 7));

        boolean run = true;

        while (run) {
            System.out.println("\n=== Gym Menu ===");
            System.out.println("1. Add Member");
            System.out.println("2. Add Trainer");
            System.out.println("3. View All");
            System.out.println("4. Demonstrate Polymorphism");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("ID: ");
                    int mid = Integer.parseInt(sc.nextLine());
                    System.out.print("Name: ");
                    String mname = sc.nextLine();
                    System.out.print("Age: ");
                    int mage = Integer.parseInt(sc.nextLine());
                    System.out.print("Type: ");
                    String type = sc.nextLine();

                    people.add(new Member(mid, mname, mage, type));
                    System.out.println("Member added");
                    break;

                case "2":
                    System.out.print("ID: ");
                    int tid = Integer.parseInt(sc.nextLine());
                    System.out.print("Name: ");
                    String tname = sc.nextLine();
                    System.out.print("Age: ");
                    int tage = Integer.parseInt(sc.nextLine());
                    System.out.print("Specialization: ");
                    String spec = sc.nextLine();
                    System.out.print("Experience: ");
                    int exp = Integer.parseInt(sc.nextLine());

                    people.add(new Trainer(tid, tname, tage, spec, exp));
                    System.out.println("Trainer added");
                    break;

                case "3":
                    System.out.println("\n--- All People ---");
                    for (Person p : people) {
                        System.out.println(p.getInfo());
                    }
                    break;

                case "4":
                    System.out.println("\n--- Polymorphism Demo ---");
                    for (Person p : people) {
                        System.out.println(p.getRole() + " -> " + p.getInfo());

                        if (p instanceof Trainer) {
                            Trainer t = (Trainer) p; // casting
                            System.out.println("   Experienced: " + t.isExperienced());
                        }
                    }
                    break;

                case "5":
                    run = false;
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Wrong option");
            }
        }
        sc.close();
    }
}
