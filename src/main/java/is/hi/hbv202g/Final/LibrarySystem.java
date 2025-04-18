package is.hi.hbv202g.Final;

import is.hi.hbv202g.Final.listener.LibraryListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibrarySystem {
  private List<Book> books;
  private List<User> users;
  private List<Lending> lendings;
  private final List<LibraryListener> listeners = new ArrayList<>();

  public void addListener(LibraryListener l) {
    listeners.add(l);
  }

  public void removeListener(LibraryListener l) {
    listeners.remove(l);
  }

  public LibrarySystem() {
    this.books = new ArrayList<>();
    this.users = new ArrayList<>();
    this.lendings = new ArrayList<>();
  }

  public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
    Book book = new Book(title, authorName);
    books.add(book);
  }

  public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
    Book book = new Book(title, authors);
    books.add(book);
  }

  public void addStudentUser(String name, boolean feePaid) {
    Student student = new Student(name, feePaid);
    users.add(student);
  }

  public void addFacultyMemberUser(String name, String department) {
    FacultyMember facultyMember = new FacultyMember(name, department);
    users.add(facultyMember);
  }

  public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new UserOrBookDoesNotExistException("Boook \"" + title + "\" does not exist.");
  }

  public User findUserByName(String name) throws UserOrBookDoesNotExistException {
    for (User user : users) {
      if (user.getName().equals(name)) {
        return user;
      }
    }
    throw new UserOrBookDoesNotExistException("User \"" + name + "\" does not exist.");
  }

  public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
    if (!users.contains(user)) {
      throw new UserOrBookDoesNotExistException("User \"" + user.getName() + "\" does not exist in the system.");
    }
    if (!books.contains(book)) {
      throw new UserOrBookDoesNotExistException("Book \"" + book.getTitle() + "\" does not exist in the system.");
    }
    Lending lending = new Lending(book, user);
    lendings.add(lending);
    for (var l : listeners)
      l.onBookBorrowed(lending);
  }

  public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
      throws UserOrBookDoesNotExistException {
    Lending foundLending = null;
    for (Lending l : lendings) {
      if (l.getBook().equals(book) && l.getUser().equals(facultyMember)) {
        foundLending = l;
        break;
      }
    }
    if (foundLending == null) {
      throw new UserOrBookDoesNotExistException("No lending found for book \""
          + book.getTitle() + "\" and faculty member \"" + facultyMember.getName() + "\".");
    }
    foundLending.setDueDate(newDueDate);
  }

  public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
    Lending foundLending = null;
    for (Lending l : lendings) {
      if (l.getBook().equals(book) && l.getUser().equals(user)) {
        foundLending = l;
        break;
      }
    }
    if (foundLending == null) {
      throw new UserOrBookDoesNotExistException("No lending found for book \""
          + book.getTitle() + "\" and user \"" + user.getName() + "\".");
    }
    lendings.remove(foundLending);
    for (var l : listeners)
      l.onBookReturned(foundLending);
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

  public void addAdminUser(String name) {
    users.add(new Admin(name));
  }

}
