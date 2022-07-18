package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
