package duksung.eegim.Navatar.domain.Cart;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.TimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Cart")
public class Cart extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNo;

    @Column(name="count", columnDefinition = "INT")
    private int count = 1;

    @Column(name="size", length=10)
    private String size;

    @ManyToOne
    @JoinColumn(name="Product_productNo")
    private Product product;

    @Column(name="User_userNo", columnDefinition = "INT")
    private Long userNo;

    @Builder
    public Cart(Long cartNo, Product product, Long userNo, int count, String size){
        this.cartNo = cartNo;
        this.product = product;
        this.userNo = userNo;
        this.count = count;
        this.size = size;
    }
}
