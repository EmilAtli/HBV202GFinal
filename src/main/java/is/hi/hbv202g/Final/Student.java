package is.hi.hbv202g.Final;

/**
 * Represents a student user in the library system, extending {@link User}.
 * Tracks whether the student has paid required library fees.
 */
public class Student extends User {

  private boolean feePaid;

  /**
   * Constructs a new Student with the given name and fee payment status.
   *
   * @param name    the name of the student; must not be null or empty
   * @param feePaid true if the student has paid fees, false otherwise
   */
  public Student(String name, boolean feePaid) {
    super(name);
    this.feePaid = feePaid;
  }

  /**
   * Checks whether the student has paid library fees.
   *
   * @return true if fees are paid, false if unpaid
   */
  public boolean isFeePaid() {
    return feePaid;
  }

  /**
   * Updates the fee payment status for the student.
   *
   * @param feePaid true if fees are now paid, false otherwise
   */
  public void setFeePaid(boolean feePaid) {
    this.feePaid = feePaid;
  }
}
