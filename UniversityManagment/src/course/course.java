package course;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class course {

    public static void logedin() {
        int[] studentIds = getStudentIds();
        
        // Assuming you have a MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            for (int j = 0; j < studentIds.length; j++) {
                int studentId = studentIds[j];
                String query1 = "SELECT students_course FROM students WHERE student_id = ?";
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                    preparedStatement1.setInt(1, studentId);

                    if (checkCourseId(studentId)) {
                        System.out.println("Course already exists for student " + studentId);
                        continue;
                    }

                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    if (resultSet1.next()) {
                        String course = resultSet1.getString("students_course");
                        System.out.println("Student " + studentId + " course = " + course);

                        if (course.equalsIgnoreCase("CSE")) {
                            String query2 = "INSERT INTO marks (student_id, course_name) VALUES (?, ?)";
                            try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
                                preparedStatement2.setInt(1, studentId);

                                String[] courses = {"JAVA", "DAA", "OS", "ECS", "WEB", "PROJ1"};
                                for (String c : courses) {
                                    preparedStatement2.setString(2, c);
                                    preparedStatement2.executeUpdate();
                                }
                            }
                            System.out.println("Student " + studentId + " is a B.Tech student");
                        } else if (course.equalsIgnoreCase("BBA")) {
                            String query2 = "INSERT INTO marks (student_id, course_name) VALUES (?, ?)";
                            try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
                                preparedStatement2.setInt(1, studentId);

                                String[] courses = {"FSA", "IFS", "IBM", "CB"};
                                for (String c : courses) {
                                    preparedStatement2.setString(2, c);
                                    preparedStatement2.executeUpdate();
                                }
                            }
                            System.out.println("Student " + studentId + " is a BBA student");
                        } else {
                            System.out.println("Error in getting student course for student " + studentId);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int[] getStudentIds() {
        int rowCount = county();

        String url = "jdbc:mysql://localhost:3306/university";
        String user = "root";
        String password = "root";

        int[] studentIds = new int[rowCount];

        try {

            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            String query = "SELECT student_id FROM students LIMIT " + rowCount;

            ResultSet resultSet = statement.executeQuery(query);

            int i = 0;
            while (resultSet.next()) {
                studentIds[i++] = resultSet.getInt("student_id");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentIds;
    }

    public static int county() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        int rowCount = 0;

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT COUNT(*) FROM students";

            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
                resultSet.next();
                rowCount = resultSet.getInt(1);
                System.out.println("Number of rows in students table: " + rowCount);
            } catch (SQLException e) {
                System.out.println("Error in getting student count");
            }
        } catch (SQLException e) {
            System.out.println("Error in getting student count");
        }

        return rowCount;
    }

    private static boolean checkCourseId(int studentId) {
        boolean exists = false;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("SELECT 1 FROM marks WHERE student_id= ?")) {
            pstmt.setInt(1, studentId);

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
