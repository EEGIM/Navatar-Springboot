package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Product.Product;
import lombok.Getter;

// 사용안함,,
@Getter
public class ProductListDto {

    private Long productNo;
    private String name;
    private String mainImage;
    private int normalPrice;
    private int salePrice;
    private boolean ARFitting;
    private String categoryCode;
    private String brand;

    public ProductListDto(Product entity){
        this.productNo = entity.getProductNo();
        this.name = entity.getName();
        this.mainImage = entity.getMainImage();
        this.normalPrice = entity.getNormalPrice();
        if (entity.getSalePrice() != null){
            this.salePrice = entity.getSalePrice();
        } else {
            this.salePrice = -1;
        }
        this.ARFitting = entity.isARFitting();
        this.categoryCode = entity.getCategoryCode();
        this.brand = entity.getBrand();
    }
}
