package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class course {

    public static String logedin(String a) {
        // Assuming you have a MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        String b = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Check the database for the entered username and password
            String query = "SELECT student_id FROM students WHERE students_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, a);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String studentId = resultSet.getString("student_id");
                    System.out.println("Roll_No= " + studentId);
                    b = studentId;

                    // Get student's course
                    String query1 = "SELECT students_course FROM students WHERE student_id = ?";
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                        preparedStatement1.setString(1, studentId);

                        ResultSet resultSet1 = preparedStatement1.executeQuery();

                        if (resultSet1.next()) {
                            String course = resultSet1.getString("students_course");
                            System.out.println("student course = " + course);
                            if (course.equalsIgnoreCase("CSE")) {
                                String query2 = "INSERT INTO marks (student_id, course_name)" + "VALUES (?, ?);";
                                try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
                                    preparedStatement2.setString(1, studentId);
                                    for (int i = 0; i < 6; i++) {
                                        if (i == 0) {
                                            String c = "JAVA";
                                            preparedStatement2.setString(2, c);
                                        } else if (i == 1) {
                                            String c = "DAA";
                                            preparedStatement2.setString(2, c);
                                        } else if (i == 2) {
                                            String c = "OS";
                                            preparedStatement2.setString(2, c);
                                        } else if (i == 3) {
                                            String c = "ECS";
                                            preparedStatement2.setString(2, c);
                                        } else if (i == 4) {
                                            String c = "WEB";
                                            preparedStatement2.setString(2, c);
                                        } else if (i == 5) {
                                            String c = "PROJ1";
                                            preparedStatement2.setString(2, c);
                                        }

                                        preparedStatement2.executeUpdate();
                                    }
                                }
                                System.out.println("You are a B.Tech student");
                            } else if (course.equalsIgnoreCase("BBA")) {
                                System.out.println("You are a BBA student");
                            } else {
                                System.out.println("Error in getting student course");
                                return null;
                            }
                        }
                    }
                } else {
                    System.out.println("Error in getting student id");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return b;
    }
    public static void checkcourseexist(String a){
      return;
    }
}
