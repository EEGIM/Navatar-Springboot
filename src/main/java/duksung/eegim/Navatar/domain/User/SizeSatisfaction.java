package duksung.eegim.Navatar.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SizeSatisfaction {
    NONE("."),
    SMALL("작음"),
    BIG("큼"),
    FIT("적당함"),;
    private final String title;

}
