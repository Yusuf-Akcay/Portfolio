package de.yusuf.controller.employeeManager;

import de.yusuf.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller("userController")
@SessionScope
public class UserController extends AbstractController  {

  private final List<User> allUser;
  private User detailingUser;

  public UserController( UserService userService ) {
    this.allUser = userService.allUser();
  }

  @Override public void init() {
  }

  public List<User> getAllUser() {
    return allUser;
  }

  public User getDetailingUser() {
    return detailingUser;
  }

  public void setDetailingUser( User detailingUser ) {
    this.detailingUser = detailingUser;
  }
}
