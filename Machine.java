import java.util.Arrays;
import java.util.Scanner;

public class Machine {

    static Scanner sc = new Scanner(System.in);

    
static int[] tray={10,10,10,10};
    static String[] denominations={"2000","500","200","100"};

    public static void loadMoney(){
        for(int i=0;i<4;i++){
            System.out.println("Enter number of "+denominations[i]+" notes : ");
            tray[i]+=Integer.parseInt(sc.nextLine());
        }
        System.out.println(Arrays.toString(tray));
    }

    public static void viewTrayDetails(){
        long n=0;

        System.out.println("Denominations");
        System.out.println();
        for(int i=0;i<4;i++){
            System.out.println(denominations[i]+"--->"+tray[i]);
            n+=(tray[i]*(Integer.parseInt(denominations[i])));
        }

        System.out.println();
        System.out.println();
        System.out.println("Total Amount --->"+n);
    }

    public static void withdrawMoney(User user){
        System.out.print("Enter Amount : ");
        int withdraw=Integer.parseInt(sc.nextLine());
        if((((withdraw%2000)%500)%200)%100!=0){
                System.out.println("Amount "+withdraw+" cannot be dispatched");return;
        }
        if(user.bankBalance>=withdraw){
        user.bankBalance-=withdraw;
        String save = String.format("\nSuccessfully withdrawn Rs.%d",withdraw);
        while(withdraw!=0){
            if(withdraw>=2000 && tray[0]>0){
                withdraw-=2000;
                tray[0]--;
            }
            else if(withdraw>500 && tray[1]>0){
                withdraw-=500;
                tray[1]--;
            }
            else if(withdraw>200 && tray[2]>0){
                withdraw-=200;
                tray[2]--;
            }
            else if(withdraw>100 && tray[3]>0){
                withdraw-=100;
                tray[3]--;
            }
        }
        System.out.println("Withdrawan Successfully");
        user.transactions+=save;
    }
    else{
        System.out.println("Insufficient Balance");
    }
    System.out.println(user.bankBalance);
    }
    public static void deposit(User user){
        System.out.println("Enter Amount to be Deposited : ");
        int n=Integer.parseInt(sc.nextLine()),tts=0,ths=0,fhs=0,hs=0;
        int sum=0;
        System.out.print("Enter no. of 2000 Rs. Notes : ");
        tts=Integer.parseInt(sc.nextLine());
        sum+=(tts*2000);
        System.out.print("Enter no. of 500 Rs. Notes : ");
        fhs=Integer.parseInt(sc.nextLine());
        sum+=(fhs*500);
        System.out.print("Enter no. of 200 Rs. Notes : ");
        ths=Integer.parseInt(sc.nextLine());
        sum+=(ths*200);
        System.out.print("Enter no. of 100 Rs. Notes : ");
        hs=Integer.parseInt(sc.nextLine());
        sum+=(hs*100);
        if(sum==n){
            tray[0]+=tts;
            tray[1]+=fhs;
            tray[2]+=ths;
            tray[3]+=hs;
            user.bankBalance+=n;
            System.out.println("Successfully deposied");
            String save = String.format("\nSuccessfully deposied Rs.%d",n);
            user.transactions+=save;
        }
        else{
            System.out.println("Incorrect Denominations");deposit(user);
        }
    }
}
