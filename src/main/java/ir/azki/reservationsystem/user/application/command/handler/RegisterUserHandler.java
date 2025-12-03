package ir.azki.reservationsystem.user.application.command.handler;

import ir.azki.reservationsystem.common.exception.UsernameAlreadyExistsException;
import ir.azki.reservationsystem.user.application.command.RegisterUserCommand;
import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void handle(RegisterUserCommand command) {
        if (userRepository.findByUsername(command.username()).isPresent())
            throw new UsernameAlreadyExistsException(command.username());

        User user = new User(command.username(), passwordEncoder.encode(command.password()), command.email());
        userRepository.save(user);
    }

}
