import java.util.Scanner;

class BankAcc {
    private double balance;

    public BankAcc(double intialBalance){
        this.balance = intialBalance;
    }
    public double getBalance(){
        return balance;
    }
    public void deposite(double amount){
        if (amount > 0){
            balance += amount;
            System.out.println("Deposite amount "+amount+ " is successful.\nCurrent Balance: "+balance);
        }
        else{
            System.out.println("Invalid Deposit amount. ");
        }
    }
    public void withdraw(double amount){
        if (amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println("Withdraw amount "+amount+" is Successful.\nCurrent Balance: "+balance);
        }
        else{
            System.out.println("Invalid Withdraw amount || Insufficient balance. ");
        }
    }
}

class Atm{
    private BankAcc bankAcc;

    public Atm(BankAcc bankAcc){
        this.bankAcc = bankAcc;
    }
    public void display(){
        System.out.println("ATM INTERFACE");
        System.out.println("1.Check Balance ");
        System.out.println("2.Deposite ");
        System.out.println("3.Withdraw ");
        System.out.println("4.Exit ATM");
    }
    public void transcation(int choice,Scanner scan){
        switch (choice){
            case 1:
                System.out.println("Current Balance: "+bankAcc.getBalance());
                break;
            case 2:
                System.out.println("Enter Deposite Amount: ");
                double depositeAm = scan.nextDouble();
                bankAcc.deposite(depositeAm);
                break;
            case 3:
                System.out.println("Enter Withdraw Amount: ");
                double withdrawAm = scan.nextDouble();
                bankAcc.withdraw(withdrawAm);
                break;
            case 4:
                System.out.println("Exit from ATM\nSee you again...");
                scan.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please select correct choice option to proceed further.");
        }
    }
}

public class atminterface{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Initial Account Balance Amount: ");
        double intialBalance = scan.nextDouble();
        BankAcc bankAcc = new BankAcc(intialBalance);
        Atm atm = new Atm(bankAcc);

        while(true){
            atm.display();
            System.out.println("Select the Option: ");
            int choice = scan.nextInt();
            atm.transcation(choice, scan);
        }
    }
}