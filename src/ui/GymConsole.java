package ui;

import model.Member;
import model.Person;
import model.Trainer;
import exception.InvalidDataException;

import java.util.ArrayList;
import java.util.Scanner;

public class GymConsole implements Menu {

    private final ArrayList<Person> people = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public GymConsole() {
        try {
            people.add(new Member(1, "Dias", 22, "Basic"));
            people.add(new Trainer(2, "Mik", 30, "Power", 7));
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start() {
        boolean run = true;
        while (run) {
            printMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addMember(); break;
                case "2": addTrainer(); break;
                case "3": viewAll(); break;
                case "4": demoPolymorphism(); break;
                case "5": run = false; break;
                default: System.err.println("Wrong option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Gym Console ===");
        System.out.println("1. Add Member");
        System.out.println("2. Add Trainer");
        System.out.println("3. View All");
        System.out.println("4. Demonstrate Polymorphism");
        System.out.println("5. Exit");
        System.out.print("Choice: ");
    }

    private void addMember() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Type: ");
            String type = sc.nextLine();

            people.add(new Member(id, name, age, type));
            System.out.println("Member added");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void addTrainer() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Specialization: ");
            String spec = sc.nextLine();
            System.out.print("Experience: ");
            int exp = Integer.parseInt(sc.nextLine());

            people.add(new Trainer(id, name, age, spec, exp));
            System.out.println("Trainer added");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void viewAll() {
        System.out.println("\n--- All People ---");
        for (Person p : people) {
            System.out.println(p.getInfo());
        }
    }

    private void demoPolymorphism() {
        System.out.println("\n--- Polymorphism Demo ---");
        for (Person p : people) {
            System.out.println(p.getRole() + " -> " + p.getInfo());

            if (p instanceof Trainer) {
                Trainer t = (Trainer) p;
                System.out.println("   Experienced: " + t.isExperienced());
            }
        }
    }
}
