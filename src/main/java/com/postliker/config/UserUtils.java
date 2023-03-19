package com.postliker.config;

import com.postliker.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

  private UserUtils() {}

  public static String getLoggedUserName() {
    return getLoggedUser().getName();
  }

  public static String getLoggedUserId() {
    return getLoggedUser().getId();
  }

  public static User getLoggedUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public static boolean isAnonymous() {
    return SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
  }

}
