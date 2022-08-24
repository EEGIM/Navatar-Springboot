package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.User.Like;
import duksung.eegim.Navatar.domain.id.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
    List<Like> findByUserNo(Long UserNo);
}
