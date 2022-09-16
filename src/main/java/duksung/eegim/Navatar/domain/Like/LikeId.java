package duksung.eegim.Navatar.domain.Like;

import duksung.eegim.Navatar.domain.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeId implements Serializable {
    private Long productNo;
    private Long userNo;
}
