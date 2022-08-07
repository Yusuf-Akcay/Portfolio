package de.yusuf.controller.employeeManager;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService( UserRepository userRepository) {

  public UserService( UserRepository userRepository ) {
    this.userRepository = userRepository;
    userRepository.save( new User( "test@example.com", "passwort", "Max", "Mustermann", "Senior Java Entwickler", Role.ADMIN ) );
    userRepository.save( new User( "guest@example.com", "password", "Erika", "Musterfrau", "Junior UI/UX Designer", Role.USER ) );
  }

  public List<User> allUser() {
    return userRepository.findAll();
  }
}
