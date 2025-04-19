package is.hi.hbv202g.Final;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book with a title, one or more authors, and a certain number of
 * copies.
 * Keeps track of total copies and how many are currently available.
 */
public class Book {

  private String title;
  private List<Author> authors;
  private final int totalCopies;
  private int availableCopies;

  /**
   * Constructs a book with a single author and one copy.
   *
   * @param title      the title of the book; must not be null or empty
   * @param authorName the name of the sole author; must not be null or empty
   * @throws EmptyAuthorListException if authorName is null or empty
   */
  public Book(String title, String authorName) throws EmptyAuthorListException {
    this(title, List.of(new Author(authorName)), 1);
  }

  /**
   * Constructs a book with a single author and the specified number of copies.
   *
   * @param title      the title of the book; must not be null or empty
   * @param authorName the name of the sole author; must not be null or empty
   * @param copies     the total number of copies; must be at least 1
   * @throws EmptyAuthorListException if authorName is null or empty
   * @throws IllegalArgumentException if copies is less than 1
   */
  public Book(String title, String authorName, int copies) throws EmptyAuthorListException {
    this(title, List.of(new Author(authorName)), copies);
  }

  /**
   * Constructs a book with multiple authors and one copy.
   *
   * @param title   the title of the book; must not be null or empty
   * @param authors the list of authors; must not be null or empty
   * @throws EmptyAuthorListException if authors is null or empty
   */
  public Book(String title, List<Author> authors) throws EmptyAuthorListException {
    this(title, authors, 1);
  }

  /**
   * Constructs a book with multiple authors and the specified number of copies.
   *
   * @param title   the title of the book; must not be null or empty
   * @param authors the list of authors; must not be null or empty
   * @param copies  the total number of copies; must be at least 1
   * @throws EmptyAuthorListException if authors is null or empty
   * @throws IllegalArgumentException if copies is less than 1
   */
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

  /**
   * Returns the title of this book.
   *
   * @return the book title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets or updates the title of this book.
   *
   * @param title the new title; must not be null or empty
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns an unmodifiable list of authors of this book.
   *
   * @return a read-only list of authors
   */
  public List<Author> getAuthors() {
    return List.copyOf(authors);
  }

  /**
   * Replaces the current list of authors with the provided list.
   *
   * @param authors the new list of authors; must not be null or empty
   * @throws EmptyAuthorListException if authors is null or empty
   */
  public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
    if (authors == null || authors.isEmpty())
      throw new EmptyAuthorListException("Author list cannot be empty.");
    this.authors = new ArrayList<>(authors);
  }

  /**
   * Adds an additional author to this book.
   *
   * @param author the author to add; should not be null
   */
  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  /**
   * Returns the total number of copies for this book.
   *
   * @return the total copies count
   */
  public int getTotalCopies() {
    return totalCopies;
  }

  /**
   * Returns the number of copies currently available for borrowing.
   *
   * @return the available copies count
   */
  public int getAvailableCopies() {
    return availableCopies;
  }

  /**
   * Borrows one copy of the book, decreasing the available count by one.
   *
   * @throws IllegalStateException if there are no available copies left
   */
  public void borrowCopy() {
    if (availableCopies == 0) {
      throw new IllegalStateException("No copies left of \"" + title + "\"");
    }
    availableCopies--;
  }

  /**
   * Returns one copy of the book, increasing the available count up to the total.
   */
  public void returnCopy() {
    if (availableCopies < totalCopies) {
      availableCopies++;
    }
  }
}
