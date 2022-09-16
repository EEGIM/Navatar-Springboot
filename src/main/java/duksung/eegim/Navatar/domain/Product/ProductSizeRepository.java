package duksung.eegim.Navatar.domain.Product;

import duksung.eegim.Navatar.domain.Product.ProductSize;
import duksung.eegim.Navatar.domain.Product.id.ProductSizeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeId> {
    List<ProductSize> findByProductNo(Long productNo);
}
