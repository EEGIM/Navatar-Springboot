package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    List<ProductSize> findByProductNo(Long productNo);
}
