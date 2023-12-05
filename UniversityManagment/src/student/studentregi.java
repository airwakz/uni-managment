package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class studentregi {

    public void sturegi(int studentId) {
        Scanner sc = new Scanner(System.in);

        // Check if student ID already exists
        String checkQuery = "SELECT * FROM students WHERE student_id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
             PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, studentId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student ID " + studentId + " is already registered.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }

        // Get student information
        System.out.println("Enter Student Name:");
        String studentName = sc.nextLine();

        System.out.println("Enter Student Address:");
        String studentAddress = sc.nextLine();

        System.out.println("Enter Student Email:");
        String studentEmail = sc.nextLine();

        System.out.println("Enter Course (e.g., B.Tech, BBA):");
        String studentCourse = sc.nextLine();

        System.out.println("Enter Branch (e.g., CSE, ME, BBA):");
        String studentBranch = sc.nextLine();

        System.out.println("Enter Father's Name:");
        String fatherName = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phoneNumber = sc.nextLine();
        
        while ((phoneNumber.length() != 10)||(phoneNumber.matches("\\d+")==false)) {
            System.out.println("Phone number must be 10 digits. Please provide a valid phone number.");
            System.out.println("Enter Phone Number:");
            phoneNumber = sc.nextLine();
        }


        System.out.print("Enter Start Year:");
        String startYear =  sc.nextLine();
        while((startYear.matches("\\d+")==false)||(startYear.length() !=4)){
            System.out.println("Enter a Valid Year");
            System.out.println("Enter Start Year:");
            startYear =  sc.nextLine();
        
        }
        
        System.out.print("Enter 10th Marks:");
        int marks10th = sc.nextInt();
        while(marks10th >100){
            System.out.println("Marks cannot be greater than 100");
            System.out.print("Enter 10th Marks:");
            marks10th = sc.nextInt();
        }
        System.out.println("Enter 12th Marks:");
        int marks12th = sc.nextInt();
        while(marks12th > 100){
            System.out.println("Marks cannot be greater than 100");
            System.out.println("Enter 12th Marks:");
            marks12th = sc.nextInt();
        }

        // Insert student information into database
        String insertQuery = "INSERT INTO students(student_id, students_name, students_address, students_email, " +
                "start_year, students_branch, students_course, father_name, phone_no, marks_10, marks_12) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
             PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, studentId);
            insertStmt.setString(2, studentName);
            insertStmt.setString(3, studentAddress);
            insertStmt.setString(4, studentEmail);
            insertStmt.setString(5, startYear);
            insertStmt.setString(6, studentBranch);
            insertStmt.setString(7, studentCourse);
            insertStmt.setString(8, fatherName);
            insertStmt.setString(9, phoneNumber);
            insertStmt.setInt(10, marks10th);
            insertStmt.setInt(11, marks12th);

            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student registered successfully.");
            } else {
                System.out.println("Error registering student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }
}
