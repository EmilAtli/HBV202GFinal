package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.LibraryListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core system for managing library operations, including adding books and
 * users,
 * handling lendings, and notifying registered listeners of events.
 */
public class LibrarySystem {

  private final List<Book> books;
  private final List<User> users;
  private final List<Lending> lendings;
  private final List<LibraryListener> listeners = new ArrayList<>();

  /**
   * Constructs a new, empty LibrarySystem with no books, users, or lendings.
   */
  public LibrarySystem() {
    this.books = new ArrayList<>();
    this.users = new ArrayList<>();
    this.lendings = new ArrayList<>();
  }

  /**
   * Registers a listener to receive library events (e.g., borrow or return).
   *
   * @param l the listener to add; must not be null
   */
  public void addListener(LibraryListener l) {
    listeners.add(l);
  }

  /**
   * Unregisters a previously added listener.
   *
   * @param l the listener to remove; must not be null
   */
  public void removeListener(LibraryListener l) {
    listeners.remove(l);
  }

  // --- Book adding methods ---

  /**
   * Adds a book with a single author and one copy to the catalog.
   *
   * @param title      the title of the book; must not be null or empty
   * @param authorName the name of the sole author; must not be null or empty
   */
  public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
    addBookWithTitleAndNameOfSingleAuthor(title, authorName, 1);
  }

  /**
   * Adds a book with a single author and a specified number of copies to the
   * catalog.
   *
   * @param title      the title of the book; must not be null or empty
   * @param authorName the name of the sole author; must not be null or empty
   * @param copies     the number of copies; must be at least 1
   */
  public void addBookWithTitleAndNameOfSingleAuthor(
      String title, String authorName, int copies) {
    try {
      books.add(new Book(title, authorName, copies));
    } catch (EmptyAuthorListException e) {
      // Should not occur with a single author name
    }
  }

  /**
   * Adds a book with multiple authors and one copy to the catalog.
   *
   * @param title   the title of the book; must not be null or empty
   * @param authors the list of authors; must not be null or empty
   * @throws EmptyAuthorListException if authors list is null or empty
   */
  public void addBookWithTitleAndAuthorList(String title, List<Author> authors)
      throws EmptyAuthorListException {
    addBookWithTitleAndAuthorList(title, authors, 1);
  }

  /**
   * Adds a book with multiple authors and a specified number of copies.
   *
   * @param title   the title of the book; must not be null or empty
   * @param authors the list of authors; must not be null or empty
   * @param copies  the number of copies; must be at least 1
   * @throws EmptyAuthorListException if authors list is null or empty
   */
  public void addBookWithTitleAndAuthorList(
      String title, List<Author> authors, int copies)
      throws EmptyAuthorListException {
    books.add(new Book(title, authors, copies));
  }

  /**
   * Adds an omnibus (collection of volumes) to the catalog, including
   * each volume and the composite omnibus itself.
   *
   * @param title   the omnibus title; must not be null or empty
   * @param volumes the list of volumes to include; must not be null or empty
   * @throws EmptyAuthorListException if any volume has no authors
   */
  public void addOmnibus(String title, List<Book> volumes)
      throws EmptyAuthorListException {
    for (Book vol : volumes) {
      if (!books.contains(vol)) {
        books.add(vol);
      }
    }
    books.add(new Omnibus(title, volumes));
  }

  // --- User adding methods ---

  /**
   * Registers a new student user in the system.
   *
   * @param name    the student's name; must not be null or empty
   * @param feePaid whether the student has paid fees
   */
  public void addStudentUser(String name, boolean feePaid) {
    users.add(new Student(name, feePaid));
  }

  /**
   * Registers a new faculty member user in the system.
   *
   * @param name       the faculty member's name; must not be null or empty
   * @param department the academic department; must not be null or empty
   */
  public void addFacultyMemberUser(String name, String department) {
    users.add(new FacultyMember(name, department));
  }

  /**
   * Registers a new admin user in the system.
   *
   * @param name the admin's name; must not be null or empty
   */
  public void addAdminUser(String name) {
    users.add(new Admin(name));
  }

  // --- Finders ---

  /**
   * Finds a book by its title.
   *
   * @param title the title to search for; must not be null or empty
   * @return the matching book
   * @throws UserOrBookDoesNotExistException if no book with that title exists
   */
  public Book findBookByTitle(String title)
      throws UserOrBookDoesNotExistException {
    return books.stream()
        .filter(b -> b.getTitle().equals(title))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "Book \"" + title + "\" does not exist."));
  }

  /**
   * Finds a user by name.
   *
   * @param name the user's name; must not be null or empty
   * @return the matching user
   * @throws UserOrBookDoesNotExistException if no user with that name exists
   */
  public User findUserByName(String name)
      throws UserOrBookDoesNotExistException {
    return users.stream()
        .filter(u -> u.getName().equals(name))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "User \"" + name + "\" does not exist."));
  }

  // --- Core operations ---

  /**
   * Borrows a book for a user, creating a lending record and notifying listeners.
   * Recursively handles Omnibus volumes.
   *
   * @param user the user borrowing; must be registered
   * @param book the book to borrow; must be in catalog
   * @throws UserOrBookDoesNotExistException if the user or book is not found
   */
  public void borrowBook(User user, Book book)
      throws UserOrBookDoesNotExistException {
    if (!users.contains(user)) {
      throw new UserOrBookDoesNotExistException(
          "User \"" + user.getName() + "\" is not registered.");
    }
    if (!books.contains(book)) {
      throw new UserOrBookDoesNotExistException(
          "Book \"" + book.getTitle() + "\" is not in the catalog.");
    }

    book.borrowCopy();
    Lending lending = new Lending(book, user);
    lendings.add(lending);
    listeners.forEach(l -> l.onBookBorrowed(lending));

    if (book instanceof Omnibus) {
      Omnibus omni = (Omnibus) book;
      for (Book vol : omni.getVolumes()) {
        borrowBook(user, vol);
      }
    }
  }

  /**
   * Extends the due date of a lending for a faculty member.
   *
   * @param facultyMember the faculty member who borrowed; must be registered
   * @param book          the borrowed book; must be in catalog
   * @param newDueDate    the new due date; must not be null
   * @throws UserOrBookDoesNotExistException if no matching lending is found
   */
  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
      throws UserOrBookDoesNotExistException {
    Lending found = lendings.stream()
        .filter(l -> l.getBook().equals(book) && l.getUser().equals(facultyMember))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "No lending for book \"" + book.getTitle() + "\" and faculty member \"" + facultyMember.getName() + "\"."));
    found.setDueDate(newDueDate);
  }

  /**
   * Returns a borrowed book, updates availability, removes the lending record,
   * and notifies listeners. Recursively handles Omnibus volumes.
   *
   * @param user the user returning; must be registered
   * @param book the book to return; must be in catalog
   * @throws UserOrBookDoesNotExistException if no matching lending is found
   */
  public void returnBook(User user, Book book)
      throws UserOrBookDoesNotExistException {
    Lending found = lendings.stream()
        .filter(l -> l.getBook().equals(book) && l.getUser().equals(user))
        .findFirst()
        .orElseThrow(() -> new UserOrBookDoesNotExistException(
            "No lending for book \"" + book.getTitle() + "\" and user \"" + user.getName() + "\"."));

    book.returnCopy();
    lendings.remove(found);
    listeners.forEach(lst -> lst.onBookReturned(found));

    if (book instanceof Omnibus) {
      Omnibus omni = (Omnibus) book;
      for (Book vol : omni.getVolumes()) {
        returnBook(user, vol);
      }
    }
  }

  // --- Getters for UI/tests ---

  /**
   * Returns an unmodifiable view of all books in the system.
   *
   * @return the catalog of books
   */
  public List<Book> getBooks() {
    return Collections.unmodifiableList(books);
  }

  /**
   * Returns an unmodifiable view of all registered users.
   *
   * @return the list of users
   */
  public List<User> getUsers() {
    return Collections.unmodifiableList(users);
  }

  /**
   * Returns an unmodifiable view of all current lendings.
   *
   * @return the active lendings
   */
  public List<Lending> getLendings() {
    return Collections.unmodifiableList(lendings);
  }
}
