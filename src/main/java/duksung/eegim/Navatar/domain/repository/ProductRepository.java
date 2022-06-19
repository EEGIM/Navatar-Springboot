package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryCodeLike(String categoryCode);
    Product findByProductNo(Long productNo);
}
