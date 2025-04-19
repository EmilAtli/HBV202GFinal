package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.AvailabilityListener;
import is.hi.hbv202g.Final.listener.FeeListener;
import is.hi.hbv202g.Final.ui.*;
import is.hi.hbv202g.Final.util.LibraryInitializer;

import java.util.*;

/**
 * Entry point for the library management application.
 * Initializes the library system, registers event listeners, seeds initial
 * data,
 * and runs an interactive command-line menu until the user exits.
 */
public class Main {
    /**
     * Starts the library management CLI.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);

        // Register listeners for fees and availability
        library.addListener(new FeeListener());
        library.addListener(new AvailabilityListener());

        // Seed the system with initial data
        LibraryInitializer.seed(library);

        // Prepare available commands for the menu
        List<Command> commands = List.of(
                new LoginCommand(library, session, scanner),
                new LogoutCommand(session),
                new CreateUserCommand(library, scanner),
                new AddBookCommand(library, scanner, session),
                new AddBookMultiAuthorsCommand(library, scanner, session),
                new AddOmnibusCommand(library, scanner, session),
                new ListBooksCommand(library),
                new BorrowBookCommand(library, scanner, session),
                new ReturnBookCommand(library, scanner, session),
                new ExtendLendingCommand(library, scanner, session),
                new ExitCommand());

        // Interactive loop
        while (true) {
            System.out.println("=== Library Menu ===");
            System.out.printf("Current user: %s%n%n",
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
