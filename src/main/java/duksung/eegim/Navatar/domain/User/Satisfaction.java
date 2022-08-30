package duksung.eegim.Navatar.domain.User;

import duksung.eegim.Navatar.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Satisfaction")
public class Satisfaction extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column(name="Product_productNo", columnDefinition = "INT")
    private Long productNo;

    @Column(name="weight", columnDefinition = "INT")
    private int weight;

    @Column(name="height", columnDefinition = "INT")
    private int height;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="satisfaction", columnDefinition = "INT")
    private SizeSatisfaction sizeSatisfaction;

    @Column(name="size", length = 10)
    private String size;

    @Builder
    public Satisfaction(Long pk, Long productNo, int weight, int height, SizeSatisfaction sizeSatisfaction, String size){
        this.pk = pk;
        this.productNo = productNo;
        this.weight = weight;
        this.height = height;
        this.sizeSatisfaction = sizeSatisfaction;
        this.size = size;
    }
}
