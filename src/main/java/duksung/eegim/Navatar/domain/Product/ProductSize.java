package duksung.eegim.Navatar.domain.Product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="productsize")
public class ProductSize {
    @Id
    @Column(name="productno", columnDefinition = "INT")
    private Long productNo;

    @Column(name="size", length=10)
    private String size;

}