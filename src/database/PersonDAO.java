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
}