package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductDetail;
import duksung.eegim.Navatar.domain.id.ProductDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailId> {
    List<ProductDetail> findByProductNo(Long productNo);
}
