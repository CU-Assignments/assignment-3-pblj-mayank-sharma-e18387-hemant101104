import java.util.Scanner;

class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMWithdrawalSystem {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 3000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            validatePIN(pin);

            System.out.print("Withdraw Amount: ");
            double amount = scanner.nextDouble();
            withdraw(amount);

            System.out.println("Withdrawal successful! Current Balance: " + balance);
        } catch (InvalidPINException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Current Balance: " + balance);
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }

    private static void validatePIN(int pin) throws InvalidPINException {
        if (pin != CORRECT_PIN) {
            throw new InvalidPINException("Error: Invalid PIN.");
        }
    }

    private static void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Error: Insufficient balance.");
        }
        balance -= amount;
    }
}
