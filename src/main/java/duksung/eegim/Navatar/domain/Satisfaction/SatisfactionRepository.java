package duksung.eegim.Navatar.domain.Satisfaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatisfactionRepository extends JpaRepository<Satisfaction, Long> {
    // 단계도 사용자가 정할 수 있도록...?
    @Query(value = "select Product_productNo from (select * from Satisfaction where height between :height-3 and :height+3 AND weight between :weight-3 and :weight+3) s Group by s.Product_productNo having count(*) > 1 ORDER BY (select COUNT(*) from (select * from Satisfaction where height between 160 and 165 AND weight between 50 and 60) v where v.satisfaction=2 and v.Product_productNo=s.Product_productNo)/COUNT(s.Product_productNo) desc, s.Product_productNo desc;", nativeQuery = true)
    List<Long> getProductNoList(@Param("height") Long height, @Param("weight") Long weight);
}
