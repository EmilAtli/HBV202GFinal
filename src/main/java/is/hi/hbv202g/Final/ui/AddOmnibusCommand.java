package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.EmptyAuthorListException;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddOmnibusCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  public AddOmnibusCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  @Override
  public String name() {
    return "Bundle Books into Set (admins only)";
  }

  @Override
  public void execute() {
    // only admins may bundle
    if (!(session.getCurrentUser() instanceof Admin)) {
      System.err.println("! Only admins can create a bundle.\n");
      return;
    }

    List<Book> all = library.getBooks();
    if (all.size() < 2) {
      System.err.println("! Need at least two books in the catalog to bundle.\n");
      return;
    }

    System.out.println("Select books to bundle (comma-separated numbers (,)):");
    for (int i = 0; i < all.size(); i++) {
      Book b = all.get(i);
      System.out.printf("  %d) %s%n", i + 1, b.getTitle());
    }
    System.out.print("> ");
    String line = scanner.nextLine().trim();
    String[] parts = line.split(",");
    List<Book> volumes = new ArrayList<>();
    try {
      for (String p : parts) {
        int idx = Integer.parseInt(p.trim()) - 1;
        volumes.add(all.get(idx));
      }
    } catch (Exception e) {
      System.err.println("! Invalid selection. Bundle aborted.\n");
      return;
    }

    System.out.print("Enter title for this set: ");
    String setTitle = scanner.nextLine().trim();

    try {
      library.addOmnibus(setTitle, volumes);
      System.out.println("→ Created set \"" + setTitle + "\" containing:");
      volumes.forEach(v -> System.out.println("   • " + v.getTitle()));
      System.out.println();
    } catch (EmptyAuthorListException e) {
      System.err.println("! Could not create set: " + e.getMessage() + "\n");
    }
  }
}
