package is.hi.hbv202g.Final.listener;

import is.hi.hbv202g.Final.Lending;

public interface LibraryListener {
  // When a book is borrowed
  void onBookBorrowed(Lending lending);

  // When a book is returned
  void onBookReturned(Lending lending);
}
