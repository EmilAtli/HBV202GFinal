package is.hi.hbv202g.Final;

/**
 * Represents an author with a name.
 */
public class Author {

  private String name;

  /**
   * Constructs a new Author with the specified name.
   *
   * @param name the author's name; must not be null or empty
   */
  public Author(String name) {
    this.name = name;
  }

  /**
   * Returns the author's name.
   *
   * @return the name of the author
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the author's name.
   *
   * @param name the new name of the author; must not be null or empty
   */
  public void setName(String name) {
    this.name = name;
  }
}
