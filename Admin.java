import java.util.Scanner;

public class Admin {

    private static Scanner sc = new Scanner(System.in);

    private String userName;
    private String passWord;

    

    public Admin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }



    private static Admin[] admins = {new Admin("hari","1234"),new Admin("admin","01010"),new Admin("admin1","2113")};

    public static void adminLogin(){
        String uname = sc.nextLine();
        String pass = sc.nextLine();
        authenticate(uname,pass);
    }

    public static void authenticate(String uname,String pass){
        int ind=-1;
        for(int i=0;i<3;i++){
            if(admins[i].userName.equals(uname)){
                ind=i;
            }
        }
        if(ind==-1){
            System.out.println("User name not found");
        }
        else{
            if(admins[ind].passWord.equals(pass)){
                adminpage();
            }
            else{
                System.out.println("You Entered an Incorrect Password");
            }
        }
    }
    public static void adminpage(){
        int n=-1;
        do{
            System.out.println("Enter 1 to Load Amount");
            System.out.println("Enter 2 to see Denominations & Total Amount");
            System.out.println("Enter 3 to Exit");

            n=Integer.parseInt(sc.nextLine());

            if(n==1){
                Machine.loadMoney();
                System.out.println("SuccessFully Loaded");
                System.out.println();
                Machine.viewTrayDetails();    
            }
            else if(n==2){
                Machine.viewTrayDetails();
                System.out.println("Viewing");
             }
             else if(n==3){
                return;
             }
             else{
                 System.out.println("Invalid Choice !");
             }

        }while(n!=3);
    }
}
