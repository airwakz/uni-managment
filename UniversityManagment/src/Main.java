import java.util.*;
import student.*;
import regilogin.*;
import faculty.*;
public class Main
{
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);  
        System.out.println("Choose Your designation: ");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Admin");  
        System.out.println("Enter Your Choice: ");
        int choice=in.nextInt();
        switch(choice){
            case 1:
                
                System.out.println("1. Login");
                System.out.println("Enter Your Choice: ");
                int choice3=in.nextInt();
                switch(choice3){
                    
                    case 1:
                        
                        String status =login.stud("S");
                        System.out.println(status);
                           if((status != "")||(status.length() != 0)){
                            System.out.println("1. Srudent Registration");
                            System.out.println("2. View Grades");
                            System.out.println("3. View Attendance");
                            System.out.println("4. Update Password");
                            System.out.println("Enter Your Choice: ");
                            int choice4=in.nextInt();
                            switch(choice4){
                                case 1:
                                   studentregi rStudentregi=new studentregi();
                                    rStudentregi.sturegi(status);
                                    break;
                                case 2:
                                    viewgrades grades=new viewgrades();
                                    grades.markss(status);
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }

                           }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            break;

            case 2:
                
                System.out.println("1. Login");
                System.out.println("Enter Your Choice: ");
                int choice2=in.nextInt();
                switch(choice2){
                    case 1:
                        String status=login.fac("F");
                        System.out.println(status);
                        if((status != "")||(status.length() != 0)){
                            System.out.println("1. Faculty Registration");
                            System.out.println("2. Grades Submission ");   
                            System.out.println("Enter Your Choice: ");
                            int choice4=in.nextInt();
                            switch(choice4){
                                case 1:
                                   facultyregi.fac(status);
                                    break;
                                case 2:                                     
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }

                           }
                    break;
                    default:
                        System.out.println("Invalid Choice");
                break;


                }
            break;
                case 3:
                System.out.println("Login");
                String status=login.adm("A");
                System.out.println(status);
                if((status != "")||(status.length() != 0)){
                            System.out.println(" 1. User Registration");
                            System.out.println("2. Faculty Course Registration");
                            System.out.println("3. Course Registration");
                            System.out.println("4. Student Semester Registration");
                            System.out.println("5. Detials Updation"); 
                            System.out.println("Enter Your Choice: ");
                            int choice4=in.nextInt();
                            switch(choice4){
                                case 1:
                                      regi.uregi();
                                    break;
                                case 2:

                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }

                           }
                break;
                default:
                    System.out.println("Invalid Choice");

        } 
        
    }
}