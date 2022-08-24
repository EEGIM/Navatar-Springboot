package duksung.eegim.Navatar.domain.Product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="productno", columnDefinition = "INT")
    private Long productNo;

    @Column(name="name", length=100)
    private String name;

    @Column(name="mainimage", length=200)
    private String mainImage;

    @Column(name="normalprice", columnDefinition = "INT")
    private int normalPrice;

    @Column(name="saleprice", columnDefinition = "INT", nullable=true)
    private Integer salePrice;

    @Column(name="arfitting", columnDefinition = "TINYINT")
    private boolean ARFitting;

    @Column(name="categorycode", length=45)
    private String categoryCode;

    @Column(name="brand", length=50)
    private String brand;

    @Override
    public String toString(){
        return productNo+" "+name+ "입니다.";
    }

}
