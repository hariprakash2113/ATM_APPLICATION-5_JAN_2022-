import java.util.Scanner;

public class User {
    private static Scanner sc = new Scanner(System.in);
    String userName;
    String password;
    Integer bankBalance;
    Character BankID;
    Integer wpass = 0;
    String transactions = "";

    public User(String userName, String password, Integer bankBalance, Character id) {
        this.userName = userName;
        this.password = password;
        this.bankBalance = bankBalance;
        this.BankID = id;
    }

    static User[] users = {
            new User("hari", "2113", 40000, 'A'),
            new User("prakash", "qwerty", 25000, 'B'),
            new User("giri", "mnbvcxz", 70000, 'A')
    };

    static User current;

    public static void userLogin() {
        String uname = sc.nextLine();
        String pass = sc.nextLine();
        authenticate(uname, pass);
    }

    public static void authenticate(String uname, String pass) {
        int ind = -1;
        for (int i = 0; i < 3; i++) {
            if (users[i].userName.equals(uname)) {
                ind = i;
            }
        }
        if (ind == -1) {
            System.out.println("User name not found");
            userLogin();
        } else {
            try {
                if (users[ind].password.equals(pass)) {
                    current = users[ind];
                    userPage();
                } else {
                    if (users[ind].wpass > 1) {
                        System.out.println("You've Reached max. Wrong Pass Attempt limit");
                        users[ind].password = null;
                        userLogin();
                    }
                    System.out.println("You Entered an Incorrect Password");
                    users[ind].wpass++;
                    userLogin();
                }
            } catch (NullPointerException e) {
                System.out.println("You've Reached max. Wrong Pass Attempt limit\n\nTry After 24 Hours");
            }
        }
    }

    static int n = 0;

    public static void userPage() {
        current.wpass = 0;
        do {
            System.out.println("Enter 1 for Withdraw");
            System.out.println("Enter 2 for Deposit");
            System.out.println("Enter 3 for pin change");
            System.out.println("Enter 4 to view Balance");
            System.out.println("Enter 5 to Transfer");
            System.out.println("Enter 6 to view Transactions");
            System.out.println("Enter 7 to Exit");
            int opt = Integer.parseInt(sc.nextLine());
            if (opt == 1) {
                Machine.withdrawMoney(current);
            } else if (opt == 2) {
                Machine.deposit(current);
            } else if (opt == 3) {
                pinchange(current);
            } else if (opt == 4) {
                viewBalance();
            } else if (opt == 5) {
                transfer();
            } else if (opt == 6) {
                viewTranasctions();
            } else if (opt == 7) {
                return;
            } else {
                System.out.println("Invalid Input !");
            }

        } while (n != 5);
    }

    public static void viewBalance() {
        System.out.println("Your Current Balance is :- " + current.bankBalance);
    }

    public static void pinchange(User u) {
        System.out.print("Enter OTP : ");
        String otp = sc.nextLine();
        if (otp.equals("123456")) {
            System.out.print("Enter new PIN : ");
            u.password = sc.nextLine();
        }
        System.out.println("Successfully Changed");
        current = null;
        userLogin();
    }

    public static void transfer() {
        User to = current;
        System.out.print("Enter Amount need to Transfer : ");
        int Amt = Integer.parseInt(sc.nextLine());
        if (current.bankBalance >= n) {
            System.out.print("Enter Receivers usename : ");
            String receiver = sc.nextLine();
            int ind = -1;
            for (int i = 0; i < users.length; i++) {
                if (users[i].userName.equals(receiver)) {
                    to = users[i];
                    ind = 0;
                    break;
                }
            }
            if (ind == -1) {
                System.out.println("User name not Found");
                transfer();
            } else {
                to.bankBalance += Amt;
                if (current.BankID == to.BankID)
                    current.bankBalance -= Amt;
                else
                    current.bankBalance -= (Amt + 50);
                System.out.printf("Transfered Rs.%d to %s\n", Amt, current);
                current.transactions += ("\n" + String.format("Transfered Rs.%d to %s\n", Amt, current));
                viewBalance();
            }
        }
    }

    static void viewTranasctions() {
        if (current.transactions.equals("")) {
            System.out.println("No Transactions found");
            userPage();
        } else {
            System.out.println(current.transactions);
            userPage();
        }
    }
}
