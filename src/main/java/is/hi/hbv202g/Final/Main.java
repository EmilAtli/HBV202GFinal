package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.FeeListener;
import is.hi.hbv202g.Final.ui.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        library.addListener(new FeeListener());

        List<Command> commands = List.of(
                new CreateUserCommand(library, scanner),
                new AddBookCommand(library, scanner),
                new ListBooksCommand(library),
                new BorrowBookCommand(library, scanner),
                new ReturnBookCommand(library, scanner),
                new ExitCommand());

        while (true) {
            System.out.println("=== Library Menu ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, commands.get(i).name());
            }
            System.out.print("> ");
            String line = scanner.nextLine();
            try {
                int choice = Integer.parseInt(line);
                commands.get(choice - 1).execute();
            } catch (Exception e) {
                System.err.println("Invalid choice, try again.\n");
            }
        }
    }
}
