import java.util.Scanner;

public class Atm {
     private static Scanner sc;
     
    public static void main(String[] args) {
        sc=new Scanner(System.in);
        int n;
        System.out.println("------------ATM-------------");
        do{
            System.out.println("Enter 1 for Admin Login");
            System.out.println("Enter 2 for User Login");
            System.out.println("Enter 3 to Exit");
            n=Integer.parseInt(sc.nextLine());
            if(n==1){
                Admin.adminLogin();
            }
            else if(n==2){
                User.userLogin();

            }
            else if(n==3){
                System.exit(0);
            }
            else{
                System.out.println("Invalid Input !");
            }
        }while(n!=3);
    }
}
