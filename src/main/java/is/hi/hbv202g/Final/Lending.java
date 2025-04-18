package is.hi.hbv202g.Final;

import java.time.LocalDate;

public class Lending {

  private LocalDate dueDate;
  private Book book;
  private User user;

  public Lending(Book book, User user) {
    this.book = book;
    this.user = user;
    this.dueDate = LocalDate.now().plusDays(30);
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
