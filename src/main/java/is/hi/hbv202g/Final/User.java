package is.hi.hbv202g.Final;

public abstract class User {
  private String name;

  /**
   * Creates a new user with the given name.
   * Constructor
   * 
   * @param name
   */
  public User(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the user.
   * 
   * @return the name of the user
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the user.
   * 
   * @param name the new name of the user
   */
  public void setName(String name) {
    this.name = name;
  }
}
