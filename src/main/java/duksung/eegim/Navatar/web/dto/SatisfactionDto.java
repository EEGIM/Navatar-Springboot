package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Satisfaction.Satisfaction;
import duksung.eegim.Navatar.domain.Satisfaction.SizeSatisfaction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SatisfactionDto {

    private Long pk;
    private Long productNo;
    private int weight;
    private int height;
    private SizeSatisfaction sizeSatisfaction;
    private String size;

    @Builder
    public SatisfactionDto(Long productNo, int weight, int height, SizeSatisfaction sizeSatisfaction, String size){
        this.productNo = productNo;
        this.weight = weight;
        this.height = height;
        this.sizeSatisfaction = sizeSatisfaction;
        this.size = size;
    }

    public Satisfaction toEntity(){
        return Satisfaction.builder()
                .pk(pk)
                .productNo(productNo)
                .weight(weight)
                .height(height)
                .sizeSatisfaction(sizeSatisfaction)
                .size(size)
                .build();
    }

}
