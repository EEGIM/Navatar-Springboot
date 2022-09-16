package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Review.Review;
import duksung.eegim.Navatar.domain.Satisfaction.Satisfaction;
import duksung.eegim.Navatar.domain.Satisfaction.SatisfactionRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
