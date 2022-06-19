package duksung.eegim.Navatar.domain.Product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="productdetail")
public class ProductDetail {
    @Id
    @Column(name="productno", columnDefinition = "INT")
    private Long productNo;

    @Column(name="image", length=200)
    private String image;

    @Column(name="imgno", columnDefinition = "INT")
    private int imgno;
}
