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
        try {
            int age = 16;
            checkAge(age);
        } catch (AgeException e) {
            System.out.println("Custom Exception Caught: " + e.getMessage());
        }
    }
}
