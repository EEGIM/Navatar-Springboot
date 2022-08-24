package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.User.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserNo(Long userNo);
}
