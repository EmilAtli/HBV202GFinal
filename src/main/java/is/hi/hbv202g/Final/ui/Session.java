package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.User;

public class Session {
  private User currentUser;

  /**
   * Returns the user that is currently logged in.
   * 
   * @return the current user, or null if no user is logged in
   */
  public User getCurrentUser() {
    return currentUser;
  }

  /**
   * Sets the user that is currently logged in.
   * 
   * @param user the new current user, or null to log out the current user
   */
  public void setCurrentUser(User user) {
    this.currentUser = user;
  }
}
