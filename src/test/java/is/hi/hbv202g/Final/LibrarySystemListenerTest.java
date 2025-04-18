package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import is.hi.hbv202g.Final.listener.LibraryListener;

public class LibrarySystemListenerTest {
    private LibrarySystem library;
    private boolean borrowNotified;
    private boolean returnNotified;

    @Before
    public void setUp() {
        library = new LibrarySystem();
        library.addStudentUser("Alice", true);
        library.addFacultyMemberUser("Prof", "Dept");
        library.addBookWithTitleAndNameOfSingleAuthor("Test Book", "Author");

        borrowNotified = false;
        returnNotified = false;
    }

    @Test
    public void testListenersAreNotified() throws Exception {
        LibraryListener listener = new LibraryListener() {
            @Override
            public void onBookBorrowed(Lending l) {
                borrowNotified = true;
            }

            @Override
            public void onBookReturned(Lending l) {
                returnNotified = true;
            }
        };
        library.addListener(listener);

        User alice = library.findUserByName("Alice");
        Book book = library.findBookByTitle("Test Book");
        library.borrowBook(alice, book);
        assertTrue("Listener should be called on borrow", borrowNotified);

        library.returnBook(alice, book);
        assertTrue("Listener should be called on return", returnNotified);
    }
}
