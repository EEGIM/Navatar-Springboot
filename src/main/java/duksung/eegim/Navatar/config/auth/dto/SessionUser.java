package duksung.eegim.Navatar.config.auth.dto;

import duksung.eegim.Navatar.domain.User.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
