package is.hi.hbv202g.Final;

import java.util.ArrayList;
import java.util.List;

public class Book {
  private String title;
  private List<Author> authors;
  private final int totalCopies;
  private int availableCopies;

  /** Single‐author, default 1 copy */
  public Book(String title, String authorName) throws EmptyAuthorListException {
    this(title, List.of(new Author(authorName)), 1);
  }

  /** Single‐author, specified number of copies */
  public Book(String title, String authorName, int copies) throws EmptyAuthorListException {
    this(title, List.of(new Author(authorName)), copies);
  }

  /** Multi‐author, default 1 copy */
  public Book(String title, List<Author> authors) throws EmptyAuthorListException {
    this(title, authors, 1);
  }

  /** Constructor */
  public Book(String title, List<Author> authors, int copies) throws EmptyAuthorListException {
    if (authors == null || authors.isEmpty()) {
      throw new EmptyAuthorListException("Author list cannot be empty.");
    }
    if (copies < 1) {
      throw new IllegalArgumentException("There must be at least one copy.");
    }
    this.title = title;
    this.authors = new ArrayList<>(authors);
    this.totalCopies = copies;
    this.availableCopies = copies;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Author> getAuthors() {
    return List.copyOf(authors);
  }

  public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
    if (authors == null || authors.isEmpty())
      throw new EmptyAuthorListException("Author list cannot be empty.");
    this.authors = new ArrayList<>(authors);
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  public int getTotalCopies() {
    return totalCopies;
  }

  public int getAvailableCopies() {
    return availableCopies;
  }

  /** Decrement availability; throw if none left. */
  public void borrowCopy() {
    if (availableCopies == 0) {
      throw new IllegalStateException("No copies left of \"" + title + "\"");
    }
    availableCopies--;
  }

  /** Return a copy (up to totalCopies). */
  public void returnCopy() {
    if (availableCopies < totalCopies) {
      availableCopies++;
    }
  }
}
