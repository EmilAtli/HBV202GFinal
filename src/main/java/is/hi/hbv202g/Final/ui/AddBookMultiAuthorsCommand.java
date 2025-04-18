package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.EmptyAuthorListException;
import is.hi.hbv202g.Final.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddBookMultiAuthorsCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public AddBookMultiAuthorsCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Add Book (multiple authors)";
  }

  @Override
  public void execute() {
    System.out.print("  Title: ");
    String title = scanner.nextLine().trim();

    System.out.print("  Authors (comma‑separated): ");
    String line = scanner.nextLine().trim();
    List<Author> authors = new ArrayList<>();
    if (!line.isEmpty()) {
      authors = List.of(line.split(","))
          .stream()
          .map(String::trim)
          .filter(s -> !s.isEmpty())
          .map(Author::new)
          .collect(Collectors.toList());
    }
    System.out.print("  Number of copies: ");
    int copies = Integer.parseInt(scanner.nextLine().trim());

    try {
      library.addBookWithTitleAndAuthorList(title, authors, copies);
      System.out.println("Book added with authors: " +
          authors.stream().map(Author::getName).collect(Collectors.joining(", "))
          + "\n");
    } catch (EmptyAuthorListException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
