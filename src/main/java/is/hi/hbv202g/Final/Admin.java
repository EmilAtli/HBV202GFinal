package is.hi.hbv202g.Final;

/**
 * An {@code Admin} is a special kind of {@link User} who has
 * elevated privileges (e.g. user management, system configuration).
 */
public class Admin extends User {

  /**
   * Create a new Admin with the given display name.
   *
   * @param name the admin’s name; must not be null or empty
   */
  public Admin(String name) {
    super(name);
  }
}