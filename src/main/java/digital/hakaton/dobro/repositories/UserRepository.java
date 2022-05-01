package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findOneByUsername(String username);

    User getUserByEmail(String email);

    User getById(Long userId);
}