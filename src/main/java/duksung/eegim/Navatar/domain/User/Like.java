package duksung.eegim.Navatar.domain.User;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.TimeEntity;
import duksung.eegim.Navatar.domain.id.LikeId;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="Likes")
@IdClass(LikeId.class)
@EntityListeners(AuditingEntityListener.class)
public class Like implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="Product_productNo", referencedColumnName = "productno")
    private Product productNo;

    @Id
    @Column(name="User_userNo", columnDefinition = "INT", nullable = false)
    private Long userNo;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Like(Product productNo, Long userNo){
        this.productNo = productNo;
        this.userNo = userNo;
    }

}
