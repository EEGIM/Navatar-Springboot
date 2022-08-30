package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Product.Product;
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
    private Product product;
    private Long userNo;
    private int count;
    private String size;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public CartDto(Product product, String size, int count){
        this.product = product;
        this.count = count;
        this.size = size;
    }

    @Builder
    public CartDto(Product product, String size){
        this.product = product;
        this.size = size;
    }

    public Cart toEntity(){
        return Cart.builder()
                .cartNo(cartNo)
                .product(product)
                .userNo(userNo)
                .count(count)
                .size(size)
                .build();
    }

}
