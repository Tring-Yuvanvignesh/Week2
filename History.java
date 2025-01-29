import java.util.Stack;

class BrowserHistory {
    private Stack<String> historyStack = new Stack<>();

    public void visitPage(String url) {
        historyStack.push(url);
        System.out.println("Visited: " + url);
    }

    public void goBack() {
        if (!historyStack.isEmpty()) {
            String lastPage = historyStack.pop();
            System.out.println("Going back from: " + lastPage);
        } 
        else {
            System.out.println("No history available!");
        }
    }

    public void currentPage() {
        if (!historyStack.isEmpty()) {
            System.out.println("Current Page: " + historyStack.peek());
        } 
        else {
            System.out.println("No pages visited yet.");
        }
    }
}

public class History {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();

        browser.visitPage("google.com");
        browser.visitPage("youtube.com");
        browser.visitPage("github.com");

        browser.currentPage();

        browser.goBack();
        browser.currentPage();

        browser.goBack();
        browser.currentPage();

        browser.goBack();
        browser.goBack(); 
    }
}
