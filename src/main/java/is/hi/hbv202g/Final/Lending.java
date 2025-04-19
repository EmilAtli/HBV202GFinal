package is.hi.hbv202g.Final;

import java.time.LocalDate;

/**
 * Represents a lending record for a book borrowed by a user,
 * including the due date for return.
 */
public class Lending {

  private LocalDate dueDate;
  private Book book;
  private User user;

  /**
   * Constructs a new Lending record for the given book and user,
   * setting the due date to 30 days from the current date.
   *
   * @param book the book being lent; must not be null
   * @param user the user borrowing the book; must not be null
   */
  public Lending(Book book, User user) {
    this.book = book;
    this.user = user;
    this.dueDate = LocalDate.now().plusDays(30);
  }

  /**
   * Returns the due date by which the book must be returned.
   *
   * @return the due date for this lending
   */
  public LocalDate getDueDate() {
    return dueDate;
  }

  /**
   * Sets a new due date for this lending record.
   *
   * @param dueDate the new due date; must not be null
   */
  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Returns the book involved in this lending.
   *
   * @return the lent book
   */
  public Book getBook() {
    return book;
  }

  /**
   * Sets or changes the book for this lending.
   *
   * @param book the book being lent; must not be null
   */
  public void setBook(Book book) {
    this.book = book;
  }

  /**
   * Returns the user who borrowed the book.
   *
   * @return the borrowing user
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets or changes the user for this lending.
   *
   * @param user the user borrowing the book; must not be null
   */
  public void setUser(User user) {
    this.user = user;
  }
}
