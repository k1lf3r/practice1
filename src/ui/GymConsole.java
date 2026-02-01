package ui;

import model.Member;
import model.Person;
import model.Trainer;
import exception.InvalidDataException;
import database.PersonDAO;
import database.DatabaseConnection;

import java.util.ArrayList;
import java.util.Scanner;

public class GymConsole implements Menu {

    private final Scanner sc = new Scanner(System.in);
    private final PersonDAO personDAO = new PersonDAO();

    public GymConsole() {
        DatabaseConnection.testConnection();

        if (personDAO.selectAll().isEmpty()) {
            try {
                Member m = new Member(1, "Dias", 22, "Basic");
                Trainer t = new Trainer(2, "Mik", 30, "Power", 7);

                personDAO.insertMember(m);
                personDAO.insertTrainer(t);
            } catch (InvalidDataException e) {
                System.err.println(e.getMessage());
            }
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
                case "4": viewMembersOnly(); break;
                case "5": viewTrainersOnly(); break;
                case "6": updatePerson(); break;
                case "7": deletePerson(); break;
                case "8": searchByName(); break;
                case "9": searchByAgeRange(); break;
                case "10": searchByMinAge(); break;
                case "11": demoPolymorphism(); break;
                case "0": run = false; break;
                default: System.err.println("Wrong option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Gym Console ===");
        System.out.println("--- PERSON MANAGEMENT ---");
        System.out.println("1. Add Member");
        System.out.println("2. Add Trainer");
        System.out.println("3. View All");
        System.out.println("4. View Members Only");
        System.out.println("5. View Trainers Only");
        System.out.println("6. Update Person");
        System.out.println("7. Delete Person");
        System.out.println("--- SEARCH & FILTER ---");
        System.out.println("8. Search by Name");
        System.out.println("9. Search by Age Range");
        System.out.println("10. Search by Min Age");
        System.out.println("--- DEMO & OTHER ---");
        System.out.println("11. Demonstrate Polymorphism");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private boolean isIdExists(int id) {
        return personDAO.findById(id) != null;
    }

    private void addMember() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (isIdExists(id)) {
                System.err.println("Error: ID " + id + " already exists");
                return;
            }

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Type: ");
            String type = sc.nextLine();

            Member member = new Member(id, name, age, type);
            personDAO.insertMember(member);
            System.out.println("Member added");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void addTrainer() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (isIdExists(id)) {
                System.err.println("Error: ID " + id + " already exists");
                return;
            }

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Specialization: ");
            String spec = sc.nextLine();
            System.out.print("Experience: ");
            int exp = Integer.parseInt(sc.nextLine());

            Trainer trainer = new Trainer(id, name, age, spec, exp);
            personDAO.insertTrainer(trainer);
            System.out.println("Trainer added");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void viewAll() {
        System.out.println("\n--- All People ---");
        ArrayList<Person> people = personDAO.selectAll();
        if (people.isEmpty()) {
            System.out.println("No people found");
            return;
        }
        for (Person p : people) {
            System.out.println(p.getInfo());
        }
    }

    private void viewMembersOnly() {
        System.out.println("\n--- All Members ---");
        ArrayList<Person> members = personDAO.selectAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found");
            return;
        }
        for (Person p : members) {
            System.out.println(p.getInfo());
        }
    }

    private void viewTrainersOnly() {
        System.out.println("\n--- All Trainers ---");
        ArrayList<Person> trainers = personDAO.selectAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No trainers found");
            return;
        }
        for (Person p : trainers) {
            System.out.println(p.getInfo());
        }
    }

    private void updatePerson() {
        try {
            System.out.print("Enter ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            Person person = personDAO.findById(id);
            if (person == null) {
                System.err.println("Person with ID " + id + " not found");
                return;
            }

            System.out.println("\nCurrent values:");
            System.out.println(person.getInfo());

            if (person instanceof Member) {
                Member m = (Member) person;

                System.out.print("New Name (current: " + m.getName() + "): ");
                String name = sc.nextLine();
                if (name.trim().isEmpty()) name = m.getName();

                System.out.print("New Age (current: " + m.getAge() + "): ");
                String ageStr = sc.nextLine();
                int age = ageStr.trim().isEmpty() ? m.getAge() : Integer.parseInt(ageStr);

                System.out.print("New Membership Type (current: " + m.getMembershipType() + "): ");
                String type = sc.nextLine();
                if (type.trim().isEmpty()) type = m.getMembershipType();

                personDAO.updateMember(id, name, age, type);

            } else if (person instanceof Trainer) {
                Trainer t = (Trainer) person;

                System.out.print("New Name (current: " + t.getName() + "): ");
                String name = sc.nextLine();
                if (name.trim().isEmpty()) name = t.getName();

                System.out.print("New Age (current: " + t.getAge() + "): ");
                String ageStr = sc.nextLine();
                int age = ageStr.trim().isEmpty() ? t.getAge() : Integer.parseInt(ageStr);

                System.out.print("New Specialization (current: " + t.getSpecialization() + "): ");
                String spec = sc.nextLine();
                if (spec.trim().isEmpty()) spec = t.getSpecialization();

                System.out.print("New Experience (current: " + t.getExperience() + "): ");
                String expStr = sc.nextLine();
                int exp = expStr.trim().isEmpty() ? t.getExperience() : Integer.parseInt(expStr);

                personDAO.updateTrainer(id, name, age, spec, exp);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void deletePerson() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            Person person = personDAO.findById(id);
            if (person == null) {
                System.err.println("Person with ID " + id + " not found");
                return;
            }

            System.out.println("\nPerson to delete:");
            System.out.println(person.getInfo());
            System.out.print("Are you sure? (yes/no): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Delete cancelled");
                return;
            }

            if (person instanceof Member) {
                personDAO.deleteMember(id);
            } else if (person instanceof Trainer) {
                personDAO.deleteTrainer(id);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        ArrayList<Person> results = personDAO.searchByName(name);

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No people found matching: " + name);
            return;
        }
        for (Person p : results) {
            System.out.println(p.getInfo());
        }
    }

    private void searchByAgeRange() {
        try {
            System.out.print("Enter minimum age: ");
            int minAge = Integer.parseInt(sc.nextLine());
            System.out.print("Enter maximum age: ");
            int maxAge = Integer.parseInt(sc.nextLine());

            ArrayList<Person> results = personDAO.searchByAgeRange(minAge, maxAge);

            System.out.println("\n--- Search Results (Age " + minAge + " - " + maxAge + ") ---");
            if (results.isEmpty()) {
                System.out.println("No people found in this age range");
                return;
            }
            for (Person p : results) {
                System.out.println(p.getInfo());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchByMinAge() {
        try {
            System.out.print("Enter minimum age: ");
            int minAge = Integer.parseInt(sc.nextLine());

            ArrayList<Person> results = personDAO.searchByMinAge(minAge);

            System.out.println("\n--- Search Results (Age >= " + minAge + ") ---");
            if (results.isEmpty()) {
                System.out.println("No people found with age >= " + minAge);
                return;
            }
            for (Person p : results) {
                System.out.println(p.getInfo());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void demoPolymorphism() {
        System.out.println("\n--- Polymorphism Demo ---");
        ArrayList<Person> people = personDAO.selectAll();
        if (people.isEmpty()) {
            System.out.println("No people found");
            return;
        }
        for (Person p : people) {
            System.out.println(p.getRole() + " -> " + p.getInfo());

            if (p instanceof Trainer) {
                Trainer t = (Trainer) p;
                System.out.println("   Experienced: " + t.isExperienced());
            }
        }
    }
}