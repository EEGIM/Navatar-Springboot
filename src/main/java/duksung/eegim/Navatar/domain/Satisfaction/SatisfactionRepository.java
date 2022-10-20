package duksung.eegim.Navatar.domain.Satisfaction;

import duksung.eegim.Navatar.web.dto.ProductSatisfactionDto;
import duksung.eegim.Navatar.web.dto.SatisfactionByProductDto;
import duksung.eegim.Navatar.web.dto.SatisfactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.lang.model.type.IntersectionType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface SatisfactionRepository extends JpaRepository<Satisfaction, Long> {
    // 단계도 사용자가 정할 수 있도록...?
    @Query(value = "select Product_productNo from (select * from Satisfaction where height between :height-3 and :height+3 AND weight between :weight-3 and :weight + 3) s Group by s.Product_productNo having count(*) > 1 ORDER BY (select COUNT(*) from (select * from Satisfaction where height between :height-3 and :height+3 AND weight between :weight-3 and :weight+3) v where v.satisfaction=3 and v.Product_productNo=s.Product_productNo)/COUNT(s.Product_productNo) desc, s.Product_productNo desc;", nativeQuery = true)
    List<Long> getProductNoList(@Param("height") Long height, @Param("weight") Long weight);

    @Query(value = "select satisfaction sizeSatisfaction, count(s.satisfaction) count, size from Satisfaction s where s.Product_productNo=:productNo group by s.satisfaction, size order by s.satisfaction desc;", nativeQuery = true)
    List<ProductSatisfactionDto> getProductSatisfaction(@Param("productNo") Long productNo);
//
//    @Query(value = "select satisfaction sizeSatisfaction, count(s.satisfaction) count, size from Satisfaction s where s.Product_productNo=:productNo and s.height between :height-2 and :height+2 and s.weight between :weight-3 and :weight+3 group by s.satisfaction, size order by s.satisfaction desc;", nativeQuery = true)
//    List<ProductSatisfactionDto> getProductSatisfaction(@Param("productNo") Long productNo, @Param("height") int height, @Param("weight") int weight);

}
