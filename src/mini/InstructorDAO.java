package mini;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {
    private Connection connection;

    public InstructorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addInstructor(Instructor instructor) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO instructors (instructorId, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, instructor.getInstructorId());
            statement.setString(2, instructor.getFirstName());
            statement.setString(3, instructor.getLastName());
            statement.setString(4, instructor.getEmail());
            statement.setString(5, instructor.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM instructors")) {
            while (rs.next()) {
                int instructorId = rs.getInt("instructorId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");

                Instructor instructor = new Instructor(instructorId, firstName, lastName, email, phoneNumber);
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }
}
