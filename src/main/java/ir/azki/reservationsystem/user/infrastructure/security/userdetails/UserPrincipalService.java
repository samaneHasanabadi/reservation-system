package ir.azki.reservationsystem.user.infrastructure.security.userdetails;

import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Cacheable(value = "user")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found with username " + username));
        return new UserPrincipal(user);
    }

}
