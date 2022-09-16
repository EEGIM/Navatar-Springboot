package duksung.eegim.Navatar.domain.Product;

import duksung.eegim.Navatar.domain.Product.id.ProductSizeId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(ProductSizeId.class)
@Table(name="productsize")
public class ProductSize {
    @Id
    @Column(name="productNo", columnDefinition = "INT")
    private Long productNo;

    @Id
    @Column(name="size", length=10)
    private String size;

}