package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    List<ProductDetail> findByProductNo(Long productNo);
}
