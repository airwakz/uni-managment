package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class studentregi {
 
public void sturegi(String name) {
    Scanner sc = new Scanner(System.in);

      String student_name = name;
      System.out.println(student_name);

      System.out.print("Enter the Address of the student : ");
      String student_address = sc.nextLine();

      System.out.print("Enter the Email of the student : ");
      String student_email = sc.nextLine();
      System.out.print("Enter the Course of the student : (B.Tech, BBA )");
      String student_course = sc.nextLine();
      System.out.print("Enter the Branch of the student (CSE,ME,BBA,) : ");
      String student_branch = sc.nextLine();

      System.out.print("Enter the Father's Name of the student : ");
      String father_name = sc.nextLine();

      System.out.print("Enter the Phone no of the student : ");
      String phone_no = sc.nextLine();

      System.out.print("Enter the Start year of the student : ");
      String start_year = sc.nextLine();

      System.out.print("Enter the 10th marks of the student : ");
      String marks_10 = sc.nextLine();

      System.out.print("Enter the 12th marks of the student : ");
      String marks_12 = sc.nextLine();

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "root");
            PreparedStatement pstm = null;

            String query = "INSERT INTO students (students_name, students_address,students_email,start_year,students_branch, students_course,father_name,phone_no,marks_10,marks_12) VALUES (?,?,?,?,?,?,?,?,?,?);";
            pstm = con.prepareStatement(query);
            pstm.setString(1, student_name);
            pstm.setString(2, student_address);
            pstm.setString(3, student_email);
            pstm.setString(4, start_year);
            pstm.setString(5, student_course);
            pstm.setString(6, student_branch);
            pstm.setString(7, father_name);
            pstm.setString(8, phone_no);
            pstm.setString(9, marks_10);
            pstm.setString(10, marks_12);
            int status = pstm.executeUpdate();

            if (status > 0) {
                System.out.println("Record is inserted");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }
}