import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
    private Map<String, User> users;

    public BankingSystem() {
        this.users = new HashMap<>();
    }

    // Method to register a new user
    public void registerUser(String username, String password, String fullName, String email) {
        if (!users.containsKey(username)) {
            User newUser = new User(username, password, fullName, email);
            users.put(username, newUser);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists. Please choose another username.");
        }
    }

    // Method to authenticate user login
    public User authenticateUser(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return user;
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("User not found. Please register.");
        }
        return null;
    }

    // Main method to simulate the banking system
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to Online Banking System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    System.out.print("Enter full name: ");
                    String regFullName = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String regEmail = scanner.nextLine();
                    bankingSystem.registerUser(regUsername, regPassword, regFullName, regEmail);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = bankingSystem.authenticateUser(loginUsername, loginPassword);
                    
                    if (loggedInUser != null) {
                        // Logged in successfully
                        while (true) {
                            System.out.println("Welcome, " + loggedInUser.getFullName() + "!");
                            System.out.println("1. Deposit funds");
                            System.out.println("2. Withdraw funds");
                            System.out.println("3. Transfer money");
                            System.out.println("4. View transaction history");
                            System.out.println("5. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            
                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter deposit amount: ");
                                    double depositAmount = scanner.nextDouble();
                                    loggedInUser.deposit(depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter withdrawal amount: ");
                                    double withdrawalAmount = scanner.nextDouble();
                                    loggedInUser.withdraw(withdrawalAmount);
                                    break;
                                case 3:
                                    System.out.print("Enter recipient's username: ");
                                    String recipientUsername = scanner.nextLine();
                                    System.out.print("Enter transfer amount: ");
                                    double transferAmount = scanner.nextDouble();
                                    User recipient = bankingSystem.users.get(recipientUsername);
                                    if (recipient != null) {
                                        loggedInUser.transfer(recipient, transferAmount);
                                    } else {
                                        System.out.println("Recipient not found.");
                                    }
                                    break;
                                case 4:
                                    loggedInUser.showTransactionHistory();
                                    break;
                                case 5:
                                    System.out.println("Logging out...");
                                    loggedInUser = null;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                            
                            if (loggedInUser == null) {
                                break; // Break out of the user menu loop if logged out
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}