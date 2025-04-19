package is.hi.hbv202g.Final;

/**
 * Abstract base class representing a user of the library system.
 */
public abstract class User {

  private String name;

  /**
   * Constructs a new User with the specified name.
   *
   * @param name the name of the user; must not be null or empty
   */
  public User(String name) {
    this.name = name;
  }

  /**
   * Returns the name of this user.
   *
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets or updates the name of this user.
   *
   * @param name the new name; must not be null or empty
   */
  public void setName(String name) {
    this.name = name;
  }
}
