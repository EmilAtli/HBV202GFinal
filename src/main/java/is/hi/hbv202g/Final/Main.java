package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        try {
            // Add books
            library.addBookWithTitleAndNameOfSingleAuthor("Effective Java", "Joshua Bloch");
            System.out.println("Added book: Effective Java");

            List<Author> authors = new ArrayList<>();
            authors.add(new Author("Robert C. Martin"));
            authors.add(new Author("Martin Fowler"));
            library.addBookWithTitleAndAuthorList("Clean Code", authors);
            System.out.println("Added book: Clean Code");

            // Add users
            library.addStudentUser("Alice", true);
            System.out.println("Added student: Alice");

            library.addFacultyMemberUser("Professor Smith", "Computer Science");
            System.out.println("Added faculty member: Professor Smith");

            // Find books and users
            Book effectiveJava = library.findBookByTitle("Effective Java");
            Book cleanCode = library.findBookByTitle("Clean Code");
            User alice = library.findUserByName("Alice");
            User profSmith = library.findUserByName("Professor Smith");

            // Borrow books
            library.borrowBook(alice, effectiveJava);
            System.out.println("Alice borrowed Effective Java");

            library.borrowBook(profSmith, cleanCode);
            System.out.println("Professor Smith borrowed Clean Code");

            // Extend lending for the faculty member
            // (For example, extend due date to 60 days from now)
            library.extendLending((FacultyMember) profSmith, cleanCode, LocalDate.now().plusDays(60));
            System.out.println("Professor Smith extended lending for Clean Code to 60 days from now");

            // Return book
            library.returnBook(alice, effectiveJava);
            System.out.println("Alice returned Effective Java");

        } catch (EmptyAuthorListException e) {
            System.err.println("Error adding book: " + e.getMessage());
        } catch (UserOrBookDoesNotExistException e) {
            System.err.println("Operation failed: " + e.getMessage());
        }
    }
}
