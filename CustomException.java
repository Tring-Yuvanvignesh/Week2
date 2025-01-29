import java.util.*;

class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}

public class CustomException {
 
    static void checkAge(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("Age must be 18 or above.");
        }
        System.out.println("Valid age: " + age);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int age;
        try {
            System.err.println("Enter the age");
            age = in.nextInt();
            checkAge(age);
        }
        catch (InputMismatchException e) {
            System.out.println("Error: Please enter a numeric value.");
        }
        catch (AgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
