package mini;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCourse(Course course) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO courses (courseId, courseName, capacity, enrolledStudents) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, course.getCapacity());
            statement.setInt(4, course.getEnrolledStudents());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM courses")) {
            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int capacity = rs.getInt("capacity");
                int enrolledStudents = rs.getInt("enrolledStudents");

                Course course = new Course(courseId, courseName, capacity);
                course.setEnrolledStudents(enrolledStudents);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
