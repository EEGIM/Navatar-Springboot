package duksung.eegim.Navatar.domain.Product.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeId implements Serializable {
    private Long productNo;
    private String size;
}
