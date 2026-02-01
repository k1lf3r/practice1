package database;

import model.Member;
import model.Trainer;
import model.Person;
import exception.InvalidDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO {

    public void insertMember(Member member) {
        String sql = "INSERT INTO members (id, name, age, membership_type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getAge());
            pstmt.setString(4, member.getMembershipType());

            pstmt.executeUpdate();
            System.out.println("Member saved to database");

        } catch (SQLException e) {
            System.err.println("Error inserting member: " + e.getMessage());
        }
    }

    public void insertTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainers (id, name, age, specialization, experience) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainer.getId());
            pstmt.setString(2, trainer.getName());
            pstmt.setInt(3, trainer.getAge());
            pstmt.setString(4, trainer.getSpecialization());
            pstmt.setInt(5, trainer.getExperience());

            pstmt.executeUpdate();
            System.out.println("Trainer saved to database");

        } catch (SQLException e) {
            System.err.println("Error inserting trainer: " + e.getMessage());
        }
    }

    public ArrayList<Person> selectAllMembers() {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("membership_type");

                list.add(new Member(id, name, age, type));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error selecting members: " + e.getMessage());
        }

        return list;
    }

    public ArrayList<Person> selectAllTrainers() {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM trainers";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");

                list.add(new Trainer(id, name, age, spec, exp));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error selecting trainers: " + e.getMessage());
        }

        return list;
    }

    public ArrayList<Person> selectAll() {
        ArrayList<Person> all = new ArrayList<>();
        all.addAll(selectAllMembers());
        all.addAll(selectAllTrainers());
        return all;
    }

    public void updateMember(int id, String name, int age, String membershipType) {
        String sql = "UPDATE members SET name = ?, age = ?, membership_type = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, membershipType);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Member updated successfully");
            } else {
                System.err.println("Member with ID " + id + " not found");
            }

        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
        }
    }

    public void updateTrainer(int id, String name, int age, String specialization, int experience) {
        String sql = "UPDATE trainers SET name = ?, age = ?, specialization = ?, experience = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, specialization);
            pstmt.setInt(4, experience);
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Trainer updated successfully");
            } else {
                System.err.println("Trainer with ID " + id + " not found");
            }

        } catch (SQLException e) {
            System.err.println("Error updating trainer: " + e.getMessage());
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Member deleted successfully");
            } else {
                System.err.println("Member with ID " + id + " not found");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
        }
    }

    public void deleteTrainer(int id) {
        String sql = "DELETE FROM trainers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Trainer deleted successfully");
            } else {
                System.err.println("Trainer with ID " + id + " not found");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting trainer: " + e.getMessage());
        }
    }

    public ArrayList<Person> searchByName(String name) {
        ArrayList<Person> result = new ArrayList<>();

        String sqlMembers = "SELECT * FROM members WHERE name ILIKE ?";
        String sqlTrainers = "SELECT * FROM trainers WHERE name ILIKE ?";
        String searchPattern = "%" + name + "%";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMembers)) {

            pstmt.setString(1, searchPattern);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String n = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("membership_type");

                result.add(new Member(id, n, age, type));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching members: " + e.getMessage());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTrainers)) {

            pstmt.setString(1, searchPattern);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String n = rs.getString("name");
                int age = rs.getInt("age");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");

                result.add(new Trainer(id, n, age, spec, exp));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching trainers: " + e.getMessage());
        }

        return result;
    }

    public ArrayList<Person> searchByAgeRange(int minAge, int maxAge) {
        ArrayList<Person> result = new ArrayList<>();

        String sqlMembers = "SELECT * FROM members WHERE age BETWEEN ? AND ? ORDER BY age DESC";
        String sqlTrainers = "SELECT * FROM trainers WHERE age BETWEEN ? AND ? ORDER BY age DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMembers)) {

            pstmt.setInt(1, minAge);
            pstmt.setInt(2, maxAge);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("membership_type");

                result.add(new Member(id, name, age, type));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching members by age: " + e.getMessage());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTrainers)) {

            pstmt.setInt(1, minAge);
            pstmt.setInt(2, maxAge);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");

                result.add(new Trainer(id, name, age, spec, exp));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching trainers by age: " + e.getMessage());
        }

        return result;
    }

    public ArrayList<Person> searchByMinAge(int minAge) {
        ArrayList<Person> result = new ArrayList<>();

        String sqlMembers = "SELECT * FROM members WHERE age >= ? ORDER BY age DESC";
        String sqlTrainers = "SELECT * FROM trainers WHERE age >= ? ORDER BY age DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMembers)) {

            pstmt.setInt(1, minAge);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("membership_type");

                result.add(new Member(id, name, age, type));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching members by min age: " + e.getMessage());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTrainers)) {

            pstmt.setInt(1, minAge);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");

                result.add(new Trainer(id, name, age, spec, exp));
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error searching trainers by min age: " + e.getMessage());
        }

        return result;
    }

    public Person findById(int id) {
        String sqlMember = "SELECT * FROM members WHERE id = ?";
        String sqlTrainer = "SELECT * FROM trainers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMember)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("membership_type");
                return new Member(id, name, age, type);
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error finding member: " + e.getMessage());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTrainer)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String spec = rs.getString("specialization");
                int exp = rs.getInt("experience");
                return new Trainer(id, name, age, spec, exp);
            }

        } catch (SQLException | InvalidDataException e) {
            System.err.println("Error finding trainer: " + e.getMessage());
        }

        return null;
    }
}