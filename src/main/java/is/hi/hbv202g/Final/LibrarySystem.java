package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.LibraryListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibrarySystem {
  private final List<Book> books;
  private final List<User> users;
  private final List<Lending> lendings;
  private final List<LibraryListener> listeners = new ArrayList<>();

  public LibrarySystem() {
    this.books = new ArrayList<>();
    this.users = new ArrayList<>();
    this.lendings = new ArrayList<>();
  }

  public void addListener(LibraryListener l) {
    listeners.add(l);
  }

  public void removeListener(LibraryListener l) {
    listeners.remove(l);
  }

  /** 2‑arg convenience: single‐author, 1 copy */
  public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
    addBookWithTitleAndNameOfSingleAuthor(title, authorName, 1);
  }

  /** Core: single‐author with specified copies */
  public void addBookWithTitleAndNameOfSingleAuthor(
      String title, String authorName, int copies) {
    try {
      books.add(new Book(title, authorName, copies));
    } catch (EmptyAuthorListException e) {
      // impossible here since we always supply one author
    }
  }

  /** 2‑arg convenience: multi‑author, 1 copy */
  public void addBookWithTitleAndAuthorList(String title, List<Author> authors)
      throws EmptyAuthorListException {
    addBookWithTitleAndAuthorList(title, authors, 1);
  }

  /** Core: multi‑author with specified copies */
  public void addBookWithTitleAndAuthorList(
      String title, List<Author> authors, int copies)
      throws EmptyAuthorListException {
    books.add(new Book(title, authors, copies));
  }

  public void addStudentUser(String name, boolean feePaid) {
    users.add(new Student(name, feePaid));
  }

  public void addFacultyMemberUser(String name, String department) {
    users.add(new FacultyMember(name, department));
  }

  public void addAdminUser(String name) {
    users.add(new Admin(name));
  }

  public Book findBookByTitle(String title)
      throws UserOrBookDoesNotExistException {
    return books.stream()
        .filter(b -> b.getTitle().equals(title))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException("Book \"" + title + "\" does not exist."));
  }

  public User findUserByName(String name)
      throws UserOrBookDoesNotExistException {
    return users.stream()
        .filter(u -> u.getName().equals(name))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException("User \"" + name + "\" does not exist."));
  }

  public void borrowBook(User user, Book book)
      throws UserOrBookDoesNotExistException {
    if (!users.contains(user)) {
      throw new UserOrBookDoesNotExistException(
          "User \"" + user.getName() + "\" does not exist in the system.");
    }
    if (!books.contains(book)) {
      throw new UserOrBookDoesNotExistException(
          "Book \"" + book.getTitle() + "\" does not exist in the system.");
    }
    book.borrowCopy();
    Lending l = new Lending(book, user);
    lendings.add(l);
    listeners.forEach(lst -> lst.onBookBorrowed(l));
  }

  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
      throws UserOrBookDoesNotExistException {
    Lending found = lendings.stream()
        .filter(l -> l.getBook().equals(book) && l.getUser().equals(facultyMember))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "No lending for book \"" + book.getTitle() +
                "\" and faculty member \"" + facultyMember.getName() + "\"."));
    found.setDueDate(newDueDate);
  }

  public void returnBook(User user, Book book)
      throws UserOrBookDoesNotExistException {
    Lending found = lendings.stream()
        .filter(l -> l.getBook().equals(book) && l.getUser().equals(user))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "No lending for book \"" + book.getTitle() +
                "\" and user \"" + user.getName() + "\"."));
    book.returnCopy();
    lendings.remove(found);
    listeners.forEach(lst -> lst.onBookReturned(found));
  }

  public List<Book> getBooks() {
    return Collections.unmodifiableList(books);
  }

  public List<User> getUsers() {
    return Collections.unmodifiableList(users);
  }

  public List<Lending> getLendings() {
    return Collections.unmodifiableList(lendings);
  }
}
