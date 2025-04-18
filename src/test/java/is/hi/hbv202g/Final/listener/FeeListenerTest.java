package is.hi.hbv202g.Final.listener;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.EmptyAuthorListException;
import is.hi.hbv202g.Final.FacultyMember;
import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.Student;

public class FeeListenerTest {
    private ByteArrayOutputStream outContent;
    private FeeListener listener;
    private Lending studentLending;
    private Lending facultyLending;

    @Before
    public void setUp() throws EmptyAuthorListException {
        // capture stdout
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        listener = new FeeListener();

        Book book = new Book("Test Book", "Author");

        // Student overdue by 5 days
        studentLending = new Lending(book, new Student("Alice", true));
        studentLending.setDueDate(LocalDate.now().minusDays(5));

        // Faculty overdue by 5 days
        facultyLending = new Lending(book, new FacultyMember("Prof", "Dept"));
        facultyLending.setDueDate(LocalDate.now().minusDays(5));
    }

    @Test
    public void testStudentOverdueFee() {
        listener.onBookReturned(studentLending);
        String output = outContent.toString().trim();
        assertTrue(output.contains("Alice owes 500.00kr for 5 days overdue"));
    }

    @Test
    public void testFacultyNoFee() {
        listener.onBookReturned(facultyLending);
        String output = outContent.toString().trim();
        assertTrue(output.contains("No fee for Prof"));
    }

    @Test
    public void testOnBookBorrowedMessage() throws EmptyAuthorListException {
        outContent.reset(); // reset output

        Book book = new Book("Test Book", "Author");
        Student alice = new Student("Alice", true);
        Lending lending = new Lending(book, alice);

        listener.onBookBorrowed(lending);

        String output = outContent.toString().trim();
        LocalDate today = LocalDate.now();
        LocalDate due = today.plusDays(30);

        String expectedStart = String.format(
                "Alice borrowed \"%s\" on %s; due on %s",
                book.getTitle(), today, due);
        assertTrue("Should announce borrow with dates",
                output.contains(expectedStart));
        assertTrue("Should mention 30-day loan",
                output.contains("(30-day loan)"));
        assertTrue("Should mention daily fee",
                output.contains("Overdue fee: 100.00kr/day"));
    }
}
