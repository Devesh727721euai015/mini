package mini;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/devmini";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Devesh@261474";

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create DAO objects for Course, Instructor, and Student
            CourseDAO courseDAO = new CourseDAO(conn);
            InstructorDAO instructorDAO = new InstructorDAO(conn);
            StudentDAO studentDAO = new StudentDAO(conn);

            // Create a scanner to read user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Add Course");
                System.out.println("2. Show All Courses");
                System.out.println("3. Add Instructor");
                System.out.println("4. Show All Instructors");
                System.out.println("5. Add Student");
                System.out.println("6. Show All Students");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addCourse(scanner, courseDAO);
                        break;
                    case 2:
                        showAllCourses(courseDAO);
                        break;
                    case 3:
                        addInstructor(scanner, instructorDAO);
                        break;
                    case 4:
                        showAllInstructors(instructorDAO);
                        break;
                    case 5:
                        addStudent(scanner, studentDAO);
                        break;
                    case 6:
                        showAllStudents(studentDAO);
                        break;
                    case 7:
                        System.out.println("Exiting the application...");
                        scanner.close();
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addCourse(Scanner scanner, CourseDAO courseDAO) {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.next();
        System.out.print("Enter Capacity: ");
        int capacity = scanner.nextInt();

        Course course = new Course(courseId, courseName, capacity);
        courseDAO.addCourse(course);
        System.out.println("Course added successfully!");
    }

    private static void showAllCourses(CourseDAO courseDAO) {
        List<Course> courses = courseDAO.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found in the database.");
        } else {
            System.out.println("All courses:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCourseId());
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Capacity: " + course.getCapacity());
                System.out.println("Enrolled Students: " + course.getEnrolledStudents());
                System.out.println();
            }
        }
    }

    private static void addInstructor(Scanner scanner, InstructorDAO instructorDAO) {
        System.out.print("Enter Instructor ID: ");
        int instructorId = scanner.nextInt();
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.next();

        Instructor instructor = new Instructor(instructorId, firstName, lastName, email, phoneNumber);
        instructorDAO.addInstructor(instructor);
        System.out.println("Instructor added successfully!");
    }

    private static void showAllInstructors(InstructorDAO instructorDAO) {
        List<Instructor> instructors = instructorDAO.getAllInstructors();
        if (instructors.isEmpty()) {
            System.out.println("No instructors found in the database.");
        } else {
            System.out.println("All instructors:");
            for (Instructor instructor : instructors) {
                System.out.println("Instructor ID: " + instructor.getInstructorId());
                System.out.println("First Name: " + instructor.getFirstName());
                System.out.println("Last Name: " + instructor.getLastName());
                System.out.println("Email: " + instructor.getEmail());
                System.out.println("Phone Number: " + instructor.getPhoneNumber());
                System.out.println();
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentDAO studentDAO) {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.next();

        Student student = new Student(studentId, firstName, lastName, email, phoneNumber);
        studentDAO.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void showAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            System.out.println("All students:");
            for (Student student : students) {
                System.out.println("Student ID: " + student.getStudentId());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Phone Number: " + student.getPhoneNumber());
                System.out.println();
            }
        }
    }
}