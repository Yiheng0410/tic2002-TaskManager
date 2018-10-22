import java.util.Scanner;

public class UI {
    private Scanner in;

    public UI() {
        in = new Scanner(System.in);
    }

    public String readUserCommand() {
        System.out.print("Your task? ");
        return in.nextLine().trim();
    }

}
