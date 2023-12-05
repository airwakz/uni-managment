package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class attendace {
    public static void view_attendance(int id){
    String jdbcUrl = "jdbc:mysql://localhost:3306/university";
        String username = "root";
        String password = "root";
        try{
            Connection con = DriverManager.getConnection(jdbcUrl,username, password);
            String query1 ="SELECT coursename , (class_attended/class_total)*100 AS Attendance FROM student_attendance WHERE  student_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query1);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Student ID: " + id);
                    System.out.println("---------------------");
                    String courseName = rs.getString("coursename");
                    int attended = rs.getInt("class_attended");
                    int total = rs.getInt("class_total");
                    int attendance = attended/total;
                    System.out.println(courseName + ": " + attendance);
                     ;
                } else {
                    System.out.println("No Attendance found for student ID: " + id);
                }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}