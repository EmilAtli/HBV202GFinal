package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.Author;

import java.util.stream.Collectors;

public class ListBooksCommand implements Command {
  private final LibrarySystem library;

  public ListBooksCommand(LibrarySystem library) {
    this.library = library;
  }

  @Override
  public String name() {
    return "List Books";
  }

  @Override
  public void execute() {
    var books = library.getBooks();
    if (books.isEmpty()) {
      System.out.println("No books in the system.\n");
      return;
    }
    System.out.println("Books:");
    for (Book b : books) {
      String authors = b.getAuthors().stream()
          .map(Author::getName)
          .collect(Collectors.joining(", "));
      System.out.printf("* Title: %s -- Author: %s%n", b.getTitle(), authors);
    }
    System.out.println();
  }
}