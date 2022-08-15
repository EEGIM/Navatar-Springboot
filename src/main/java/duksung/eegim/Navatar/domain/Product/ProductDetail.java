package duksung.eegim.Navatar.domain.Product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="productdetail")
public class ProductDetail {
    @Id
    @Column(name="productNo", columnDefinition = "INT")
    private Long productNo;

    @Column(name="image", length=200)
    private String image;

    @Column(name="imgNo", columnDefinition = "INT")
    private int imgNo;
}
