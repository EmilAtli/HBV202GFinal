package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;

import java.util.Scanner;

public class AddBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public AddBookCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Add Book";
  }

  @Override
  public void execute() {
    System.out.print("  Title: ");
    String title = scanner.nextLine().trim();

    System.out.print("  Author (single): ");
    String author = scanner.nextLine().trim();

    library.addBookWithTitleAndNameOfSingleAuthor(title, author);
    System.out.println("Book added.\n");
  }
}
