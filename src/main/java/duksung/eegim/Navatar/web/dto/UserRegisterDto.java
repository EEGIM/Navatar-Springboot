package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.User.Role;
import duksung.eegim.Navatar.domain.User.User;
import lombok.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRegisterDto {
    private Long userno;
    private String name;
    private String email;
    private Long weight;
    private Long height;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Role role;

    @Builder
    public UserRegisterDto(Long userno, String name, String email, Long weight, Long height, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.userno = userno;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.role = Role.USER;
    }

    @Builder
    public UserRegisterDto(Long weight, Long height) {
        this.weight = weight;
        this.height = height;
        this.role = Role.USER;
    }

    public User toEntity(){
        return User.builder()
                .userno(userno)
                .name(name)
                .email(email)
                .weight(weight)
                .height(height)
                .role(Role.USER)
                .build();
    }
}
