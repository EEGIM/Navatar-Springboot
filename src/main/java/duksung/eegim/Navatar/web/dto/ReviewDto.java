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

    @Builder
    public ReviewDto(String content, int rating){
        this.content = content;
        this.rating = rating;
    }

    @Builder
    public ReviewDto(ReviewDto reviewDto, Long cartNo, Long productNo){
        this.content = reviewDto.getContent();
        this.rating = reviewDto.getRating();
        this.productNo = productNo;
        this.cartNo = cartNo;
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
                .satisfaction(satisfaction)
                .build();
    }

}
