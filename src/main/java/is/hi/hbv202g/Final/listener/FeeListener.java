package is.hi.hbv202g.Final.listener;

import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * A listener that keeps track of overdue fees for students.
 */
public class FeeListener implements LibraryListener {
  private static final double DAILY_FEE = 100.0; // 100kr/day

  /**
   * Handles the event of a book being borrowed.
   * Announces the borrowing details including the user's name,
   * book title, borrowing date, due date, loan duration,
   * and the overdue fee per day.
   *
   * @param lending the Lending object containing details of the
   *                borrowed book and the user who borrowed it
   */
  @Override
  public void onBookBorrowed(Lending lending) {

    LocalDate borrowedOn = LocalDate.now();
    LocalDate dueDate = lending.getDueDate();

    // Find out how many days the loan lasts
    long loanDays = ChronoUnit.DAYS.between(borrowedOn, dueDate);

    System.out.printf(
        Locale.US,
        "%s borrowed \"%s\" on %s; due on %s (%d-day loan). Overdue fee: %.2fkr/day%n",
        lending.getUser().getName(),
        lending.getBook().getTitle(),
        borrowedOn,
        dueDate,
        loanDays,
        DAILY_FEE);
  }

  /**
   * Handles the event of a book being returned.
   * If the user is a student and the book is overdue, announces the
   * amount of the overdue fee. Otherwise, announces that no fee is owed.
   *
   * @param lending the Lending object containing details of the
   *                returned book and the user who returned it
   */
  @Override
  public void onBookReturned(Lending lending) {
    long daysOverdue = ChronoUnit.DAYS.between(lending.getDueDate(), LocalDate.now());
    if (lending.getUser() instanceof Student && daysOverdue > 0) {
      double fee = daysOverdue * DAILY_FEE;
      System.out.printf(Locale.US, "%s owes %.2fkr for %d days overdue%n",
          lending.getUser().getName(), fee, daysOverdue);
    } else {
      System.out.printf(Locale.US, "No fee for %s%n", lending.getUser().getName());
    }
  }
}