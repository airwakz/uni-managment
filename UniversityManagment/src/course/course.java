package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class course {

    public static void logedin() {
        // Assuming you have a MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        String b = "";
        int a = county();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    
                  
                    
                
                    // Get student's course
                for (int j = 1; j<= a;j++){
                    int studentId = j;
                    String query1 = "SELECT students_course FROM students WHERE student_id = ?";
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                        preparedStatement1.setInt(1, studentId);
                        if (checkcourseid(studentId)== true) {
                        System.out.println("Course alerdy exist ");
                        continue;
                        }

                        ResultSet resultSet1 = preparedStatement1.executeQuery();

                        if (resultSet1.next()) {
                            String course = resultSet1.getString("students_course");
                            System.out.println("student course = " + course);
                            if (course.equalsIgnoreCase("CSE")) {
                                String query2 = "INSERT INTO marks (student_id, course_name)" + "VALUES (?, ?);";
                                try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
                                    preparedStatement2.setInt(1, studentId);
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
                                
                            }
                        }
                        }
                
                 
            
        catch (SQLException e) {
            e.printStackTrace();
            
        }
      }
        

        
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
}
    public static int county(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        int a;
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT COUNT(*) FROM students";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                int rowCount = resultSet.getInt(1);
                System.out.println("Number of rows in customers table: " + rowCount);
                a=rowCount;
            }
            catch (SQLException e) {
                System.out.println("Error in getting student id");
                return 0;
            }
        }
        catch (SQLException e) {
            System.out.println("Error in getting student id");
            return 0;
        }
        return a;
    }
    private static boolean checkcourseid(int a) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
                PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM marks WHERE student_id= ?")) {
            pstmt.setInt(1, a);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
    
}
