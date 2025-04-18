package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.User;

public class Session {
  private User currentUser;

  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User user) {
    this.currentUser = user;
  }
}
