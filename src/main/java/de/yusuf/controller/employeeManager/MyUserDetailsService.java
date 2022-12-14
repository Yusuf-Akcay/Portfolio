package de.yusuf.controller.employeeManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    final UserRepository userRepository;

    public MyUserDetailsService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail( email );
        user.orElseThrow( () -> new UsernameNotFoundException( String.format( "Benutzer mit der E-Mail '%s' konnte nicht gefunden werden.", email ) ) );
        return user.map(MyUserDetails::new).get();
    }
}
