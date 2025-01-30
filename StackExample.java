// {{[]}} - balanced
// {{]}   - Not balanced

import java.util.Stack;

public class StackExample {
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);                             // If ch is open type then push into stack
            } 
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;                          // If ch is close First check the stack is empty or not if empty that means the string is not balanced
                }
                char lastBracket = stack.pop();             // Pop the top element for compare.
                if (!isMatchingPair(lastBracket, ch)) {    // If ch is close type then compare the peek element and ch is matching
                    return false;                           
                }
            }
        }

        return stack.isEmpty();    // If all ch is ckecked and stack is empty means The string is balanced.
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        String expression1 = "({[]})";
        String expression2 = "({[})";

        System.out.println(expression1 + " is balanced: " + isBalanced(expression1)); 
        System.out.println(expression2 + " is balanced: " + isBalanced(expression2)); 
    }
}
