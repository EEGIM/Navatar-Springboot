package duksung.eegim.Navatar.domain.repository;

import duksung.eegim.Navatar.domain.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.categoryCode like :categoryCode order by p.ARFitting desc")
    List<Product> findByCategoryCodeLike(@Param("categoryCode") String categoryCode);

    Product findByProductNo(Long productNo);

    @Override
    @Query("select p from Product p order by p.ARFitting desc")
    List<Product> findAll();
}
