package duksung.eegim.Navatar.domain.Product;

import duksung.eegim.Navatar.domain.Product.id.ProductDetailId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ProductDetailId.class)
@Table(name="productdetail")
public class ProductDetail {
    @Id
    @Column(name="productNo", columnDefinition = "INT")
    private Long productNo;

    @Column(name="image", length=200)
    private String image;

    @Id
    @Column(name="imgNo", columnDefinition = "INT")
    private int imgNo;
}
