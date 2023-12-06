import java.util.*;
import student.*;
import regilogin.*;
import faculty.*;
import course.*;
public class Main
{

    private static void student_menu(int status,String[] args){
        Scanner in = new Scanner(System.in);
        boolean i = true;
        while(i==true){
            System.out.println("1. Student Registration");
            System.out.println("2. View Grades");
            
            System.out.println("3. Update Password");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice: ");
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
                                case 3:
                                    updatepassword upd = new updatepassword();
                                    upd.updpswd(args);
                                    break;  
                                case 4:
                                    i = false;
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
                        }
    } 

    private static void faculty_menu(int status, String[] args){
        Scanner in = new Scanner(System.in);
        boolean i = true;
        while(i==true){
            System.out.println("1. Faculty Registration");
            System.out.println("2. Grades Submission "); 
            System.out.println("3. Update Password");
            System.out.println("4. Exit");  
            System.out.print("Enter Your Choice: ");
            int choice4=in.nextInt();
                            switch(choice4){
                                case 1:
                                   facultyregi.fac(status);
                                    break;
                                case 2: 
                                     marksregis.mar();                                    
                                    break;
                                case 3:
                                    updatepassword upd = new updatepassword();
                                    upd.updpswd(args);
                                    break;
                                case 4:
                                    i = false;
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
        }
    }

    private static void admin_menu(String status,String[] args){
        Scanner in = new Scanner(System.in);
        boolean i =true;
        while(i == true){
            System.out.println("1. User Registration");
            System.out.println("2. Faculty Course Registration");
            System.out.println("3. Course Registration");
            System.out.println("4. Student Details Updation"); 
            System.out.println("5. Faculty Details Updation"); 
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");
            int choice4=in.nextInt();
                            switch(choice4){
                                case 1:
                                      regi.uregi();
                                    break;
                                case 2:
                                    faccourse.faccurs();

                                    break;
                                case 3:
                                    course.logedin();
                                    break;
                                case 4:
                                changedetails sgrade = new changedetails();
                                    System.out.print("Enter the student ID : ");
                                    int id = in.nextInt();
                                    sgrade.change_student_details(id);
                                    break;
                                case 5:
                                changedetails fgrade = new changedetails();
                                    System.out.print("Enter the Faculty ID : ");
                                    int fid = in.nextInt();
                                    fgrade.change_student_details(fid);
                                    break;
                                case 6:
                                    i = false;
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
        }
    }
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);  
        System.out.println("Choose Your designation: ");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Admin");  
        System.out.print("Enter Your Choice: ");
        int choice=in.nextInt();
        switch(choice){
            case 1:
                
                System.out.println("1. Login");
                System.out.print("Enter Your Choice: ");
                int choice3=in.nextInt();
                switch(choice3){
                    
                    case 1:
                        
                        int status =login.stud("S");
                        System.out.println(status);
                           if((status != 0)){
                                Main.student_menu(status, args);
                           }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            break;

            case 2:
                
                System.out.println("1. Login");
                System.out.print("Enter Your Choice: ");
                int choice2=in.nextInt();
                switch(choice2){
                    case 1:
                        int status=login.fac("F");
                        System.out.println(status);
                        if((status != 0)){
                                Main.faculty_menu(status, args);
                           }
                    break;
                    default:
                        System.out.println("Invalid Choice");
                break;


                }
            break;
                case 3:
                System.out.println(" Login");
                
                
                        String status=login.adm("A");
                        System.out.println(status);
                        if((status != "")||(status.length() != 0)){
                        Main.admin_menu(status, args);
                        }
                    
                
                break;
                default:
                    System.out.println("Invalid Choice");
                    

        } 
        

        
    }
}