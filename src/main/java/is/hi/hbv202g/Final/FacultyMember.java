package is.hi.hbv202g.Final;

/**
 * Represents a faculty member, which is a type of {@link User},
 * and is associated with a specific academic department.
 */
public class FacultyMember extends User {

  private String department;

  /**
   * Constructs a new FacultyMember with the specified name and department.
   *
   * @param name       the name of the faculty member; must not be null or empty
   * @param department the department of the faculty member; must not be null or
   *                   empty
   */
  public FacultyMember(String name, String department) {
    super(name);
    this.department = department;
  }

  /**
   * Returns the academic department of this faculty member.
   *
   * @return the department name
   */
  public String getDepartment() {
    return department;
  }

  /**
   * Sets or updates the academic department of this faculty member.
   *
   * @param department the new department; must not be null or empty
   */
  public void setDepartment(String department) {
    this.department = department;
  }
}
