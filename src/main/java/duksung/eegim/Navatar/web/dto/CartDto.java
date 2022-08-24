package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.User.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {

    private Long cartNo;
    private Long productNo;
    private Long userNo;
    private int count;
    private String size;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public CartDto(Long productNo, String size, int count){
        this.productNo = productNo;
        this.count = count;
        this.size = size;
    }

    @Builder
    public CartDto(Long productNo, String size){
        this.productNo = productNo;
        this.size = size;
    }

    public Cart toEntity(){
        return Cart.builder()
                .cartNo(cartNo)
                .productNo(productNo)
                .userNo(userNo)
                .count(count)
                .size(size)
                .build();
    }

}
