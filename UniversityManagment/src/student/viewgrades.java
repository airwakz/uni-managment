package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class viewgrades {

    public void markss(String a) {
        System.out.println("Enter student ID: ");
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        
        int studentId=0;
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Scanner scanner = new Scanner(System.in);
            String query = "SELECT student_id FROM students WHERE students_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, a);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    studentId = resultSet.getInt("student_id");
                    System.out.println("Roll_No= " + studentId);
                    }
            }

            // Retrieve grades for the specified student ID
            String query1 = "SELECT course_name, marks FROM marks WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
                preparedStatement.setInt(1, studentId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Student ID: " + studentId);
                    System.out.println("---------------------");

                    do {
                        String courseName = resultSet.getString("course_name");
                        String marks = resultSet.getString("marks");

                        System.out.println(courseName + ": " + marks);
                    } while (resultSet.next());
                } else {
                    System.out.println("No grades found for student ID: " + studentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
