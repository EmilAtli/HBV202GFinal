package is.hi.hbv202g.Final;

public class FacultyMember extends User {
  private String department;

  // Constructor
  public FacultyMember(String name, String department) {
    super(name);
    this.department = department;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
