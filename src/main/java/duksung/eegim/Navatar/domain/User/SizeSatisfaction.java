package duksung.eegim.Navatar.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SizeSatisfaction {
    NONE("."),
    SMALL("작음"),
    FIT("적당함"),
    BIG("큼");
    private final String title;

}
