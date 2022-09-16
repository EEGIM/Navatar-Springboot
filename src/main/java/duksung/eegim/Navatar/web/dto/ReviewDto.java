package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.User.Review;
import duksung.eegim.Navatar.domain.User.Satisfaction;
import duksung.eegim.Navatar.domain.repository.SatisfactionRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long reviewNo;
    private String content;
    private int count; // 조회수
    private int rating;
    private Long productNo;
    private Long cartNo;
    private SatisfactionRepository satisfactionRepository;
    private Satisfaction satisfaction;
    private String userName;

    @Builder
    public ReviewDto(Long cartNo, Long productNo, String content, int rating, String userName){
        this.content = content;
        this.rating = rating;
        this.productNo = productNo;
        this.cartNo = cartNo;
        this.userName = userName;
        //this.satisfaction = satisfactionRepository.getOne(1L); // 임시
    }

    public Review toEntity(){
        return Review.builder()
                .reviewNo(reviewNo)
                .content(content)
                .count(count)
                .rating(rating)
                .productNo(productNo)
                .cartNo(cartNo)
                .userName(userName)
                .satisfaction(satisfaction)
                .build();
    }

}
