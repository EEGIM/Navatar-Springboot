package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.Satisfaction.SizeSatisfaction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SatisfactionByProductDto {

    private SizeSatisfaction sizeSatisfaction;
    private float rate;

    @Builder
    public SatisfactionByProductDto(SizeSatisfaction sizeSatisfaction, float rate){
        this.sizeSatisfaction = sizeSatisfaction;
        this.rate = rate;
    }
}
