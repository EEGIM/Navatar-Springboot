package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Like.Like;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {

    private Product productNo;
    private Long userNo;
    private LocalDateTime createdDate;

    @Builder
    public LikeDto(Product productNo, Long userNo){
        this.productNo = productNo;
        this.userNo = userNo;
    }

    public Like toEntity(){
        return Like.builder()
                .productNo(productNo)
                .userNo(userNo)
                .build();
    }
}
