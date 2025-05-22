//system.in import for user input
import java.util.Scanner;

//Customer class
class Customer
{
    private String name;
    private String customerId;

    public Customer(String name, String customerId)
    {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName()
    {
        return name;
    }

    public String getCustomerId()
    {
        return customerId;
    }
}

//BankAccount class
class BankAccount
{
    private String accountNumber;
    private double balance;
    private Customer customer;

    public BankAccount(String accountNumber, Customer customer)
    {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = 0.0;
    }

    public void deposit(double amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Successfully deposited $" + amount);
    }

    public void withdraw(double amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if(amount > balance)
        {
            throw new IllegalArgumentException("Insufficient funds: Cannot withdraw $" + amount + ". Current balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Successfully withdrew $" + amount);
    }

    public double getBalance()
    {
        return balance;
    }

    public String getAccountDetails()
    {
        return "Account Number: " + accountNumber + "\n" +
                "Customer: " + customer.getName() + "\n" +
                "Balance: $" + balance;
    }
}

//main class
public class BankingSystem
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        //storing customer details
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        //create customer and account
        Customer customer = new Customer(name, customerId);
        BankAccount account = new BankAccount(accountNumber, customer);

        //main loop for banking operations
        while(true)
        {
            //menu
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. View Account Details");
            System.out.println("5. Quit");
            System.out.print("Choose an option (1-5): ");

            //verify user choice
            String choiceInput = scanner.nextLine();
            int choice;
            try
            {
                choice = Integer.parseInt(choiceInput);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Error: Please enter a valid number (1-5).");
                continue;
            }

            //handling user choice
            try
            {
                switch(choice)
                {
                    //deposit
                    case 1:
                        System.out.print("Enter amount to deposit: $");
                        String depositInput = scanner.nextLine();
                        try
                        {
                            double depositAmount = Double.parseDouble(depositInput);
                            account.deposit(depositAmount);
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Error: Invalid amount entered.");
                        }
                        break;
                    //withdrawal
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        String withdrawInput = scanner.nextLine();
                        try
                        {
                            double withdrawAmount = Double.parseDouble(withdrawInput);
                            account.withdraw(withdrawAmount);
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Error: Invalid amount entered.");
                        }
                        break;
                    //check account balance
                    case 3:
                        System.out.println("Current balance: $" + account.getBalance());
                        break;
                    //view account details
                    case 4:
                        System.out.println("\n" + account.getAccountDetails());
                        break;
                    //quit
                    case 5:
                        System.out.println("Thank you for using the Banking System. Goodbye!");
                        scanner.close();
                        return;
                    //if input is invalid
                    default:
                        System.out.println("Error: Invalid option. Please choose 1-5.");
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}