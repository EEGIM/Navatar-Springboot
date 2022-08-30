package duksung.eegim.Navatar.domain.User;

import duksung.eegim.Navatar.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Review")
public class Review extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;

    @Column(name="content", length=350)
    private String content;

    @Column(name="count", columnDefinition = "INT")
    private int count = 0; // 조회수

    @Column(name="rating", columnDefinition = "tinyint")
    private int rating;

    @Column(name="Product_productNo", columnDefinition = "INT")
    private Long productNo;

    @Column(name="Cart_cartNo", columnDefinition = "INT")
    private Long cartNo;

    @OneToOne
    @JoinColumn(name="Satisfaction_pk")
    private Satisfaction satisfaction;

    @Builder
    public Review(Long reviewNo, String content, int count, int rating, Long productNo, Long cartNo, Satisfaction satisfaction){
        this.reviewNo = reviewNo;
        this.content = content;
        this.count = count;
        this.rating = rating;
        this.productNo = productNo;
        this.cartNo = cartNo;
        this.satisfaction = satisfaction;

    }

    public Review countUpdate(){
        this.count += 1;
        return this;
    }

    public Review update(int rating, String content){
        this.rating = rating;
        this.content = content;
        return this;
    }
}
