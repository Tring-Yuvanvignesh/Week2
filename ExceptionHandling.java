import java.util.*;

abstract class BankAccount {    // Absraction
    private int id;             // Encapsulation
    private String Name;
    protected double balance;

    public BankAccount(int id,String Name,double initial){
        this.id = id;
        this.Name = Name;
        this.balance = initial;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public double getBalance(){
        return balance;
    }

    public void getDetails(){
        System.out.println("Account Holder: " + Name);
        System.out.println("Account No: " + id);
        System.out.println("Current Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {      // Inheritance
    public SavingsAccount(String Name, int No, double initial) {
        super(No, Name, initial);
    }

    public void deposit(double amount) {        // Polymorphism
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }   
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        balance += amount;
        System.out.println("Deposited $" + amount + " into Savings Account.");

        //     System.out.println("Deposited $" + amount + " into Savings Account.");
        // if (amount > 0) {
        //     balance += amount;
        //     System.out.println("Deposited $" + amount + " into Savings Account.");
        // } else {
        //     System.out.println("Deposit amount must be positive.");
        // }
    }

    public void withdraw(double amount){
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            if (amount > balance) {
                throw new InsufficientException("Insufficient amount.");
            }
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " from Savings Account.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InsufficientException e) { 
            System.err.println("Error: " + e.getMessage());
        }

        // if (amount > 0 && amount <= balance) {
        //     balance -= amount;
        //     System.out.println("Withdrawn $" + amount + " from Savings Account.");
        // } else {
        //     System.out.println("Insufficient balance or invalid amount.");
        // }
    }
    
}

class InsufficientException extends Exception {
    public InsufficientException(String message) {
        super(message);
    }
}

public class ExceptionHandling {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        SavingsAccount savingsAccount = new SavingsAccount("Yuvan", 101, 1000.0);

    while (true) {
        System.out.println("\n--- --- --- --- ---");
        System.out.println("1. Display Savings Account Details");
        System.out.println("2. Deposit to Savings Account");
        System.out.println("3. Withdraw from Savings Account");
        System.out.println("4. Check Savings Account Balance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        int choice = in.nextInt();

        switch (choice) {
            case 1:
                savingsAccount.getDetails();
                break;

            case 2:
                System.out.print("Enter amount to deposit into Savings Account: ");
                double savingsDeposit = in.nextDouble();
                savingsAccount.deposit(savingsDeposit);
                break;

            case 3:
                System.out.print("Enter amount to withdraw from Savings Account: ");
                double savingsWithdraw = in.nextDouble();
                savingsAccount.withdraw(savingsWithdraw);
                break;

            case 4:
                System.out.println("Savings Account Balance: $" + savingsAccount.getBalance());
                break;

            case 5:
                System.out.println(" Goodbye ");
                return;

            default:
                System.out.println("Invalid choice! Please try again.");
        }
      }
    }
}
