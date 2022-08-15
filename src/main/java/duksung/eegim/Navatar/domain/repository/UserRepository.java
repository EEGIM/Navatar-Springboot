package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email로 이미 등록된 사용자인지 판단
}
