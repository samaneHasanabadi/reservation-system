package ir.azki.reservationsystem.user.infrastructure;

import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {

}
