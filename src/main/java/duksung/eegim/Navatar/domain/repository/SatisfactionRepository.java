package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.User.Satisfaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatisfactionRepository extends JpaRepository<Satisfaction, Long> {
}
