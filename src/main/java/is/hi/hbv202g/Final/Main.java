package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.AvailabilityListener;
import is.hi.hbv202g.Final.listener.FeeListener;
import is.hi.hbv202g.Final.ui.*;
import is.hi.hbv202g.Final.util.LibraryInitializer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);
        library.addListener(new FeeListener());
        library.addListener(new AvailabilityListener());

        LibraryInitializer.seed(library);

        List<Command> commands = List.of(
                new LoginCommand(library, session, scanner), // ← login first
                new LogoutCommand(session), // ← optional logout
                new CreateUserCommand(library, scanner),
                new AddBookCommand(library, scanner, session), // ← now requires session
                new AddBookMultiAuthorsCommand(library, scanner), // you can guard this too
                new ListBooksCommand(library),
                new BorrowBookCommand(library, scanner, session),
                new ReturnBookCommand(library, scanner, session),
                new ExtendLendingCommand(library, scanner, session), // ← guarded by session
                new ExitCommand());

        while (true) {
            System.out.println("=== Library Menu ===");
            System.out.printf("Current user: %s%n%n", // print current user.
                    session.getCurrentUser() != null
                            ? session.getCurrentUser().getName()
                            : "none");
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
