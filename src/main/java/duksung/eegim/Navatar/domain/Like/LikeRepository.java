package duksung.eegim.Navatar.domain.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
    List<Like> findByUserNo(Long UserNo);
}
