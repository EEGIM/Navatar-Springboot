package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.User.Cart;
import duksung.eegim.Navatar.domain.User.SizeSatisfaction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SatisfactionReviewDto {

    private Long productNo;
    private Long cartNo;
    private int weight;
    private int height;
    private SizeSatisfaction sizeSatisfaction;
    private String size;
    private int rating;
    private String content;
    private String userName;

    @Builder
    public SatisfactionReviewDto(Long productNo, Long cartNo, int weight, int height, SizeSatisfaction sizeSatisfaction, int rating, String content, String size, String userName){
        this.productNo = productNo;
        this.cartNo = cartNo;
        this.weight = weight;
        this.height = height;
        this.sizeSatisfaction = sizeSatisfaction;
        this.rating = rating;
        this.content = content;
        this.size = size;
        this.userName = userName;
    }

    public String toStirng(){
        return Long.toString(productNo)+ Long.toString(cartNo)+Integer.toString(weight)+Integer.toString(height)+sizeSatisfaction+rating+content;
    }

    public ReviewDto toReviewDto(){
        return ReviewDto.builder()
                .cartNo(cartNo)
                .productNo(productNo)
                .content(content)
                .rating(rating)
                .userName(userName)
                .build();
    }

    public SatisfactionDto toSatisfactionDto(){
        return SatisfactionDto.builder()
                .productNo(productNo)
                .weight(weight)
                .height(height)
                .sizeSatisfaction(sizeSatisfaction)
                .size(size)
                .build();
    }

}
