package duksung.eegim.Navatar.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailId implements Serializable {
    private Long productNo;
    private int imgNo;
}
