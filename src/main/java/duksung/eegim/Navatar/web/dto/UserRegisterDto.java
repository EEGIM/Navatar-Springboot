package duksung.eegim.Navatar.web.dto;

import duksung.eegim.Navatar.domain.User.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRegisterDto {
    private Long userno;
    private String id;
    private String password;
    private String name;
    private String email;
    private Long weight;
    private Long height;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public UserRegisterDto(Long userno, String id, String password, String name, String email, Long weight, Long height, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.userno = userno;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public User toEntity(){
        return User.builder()
                .userno(userno)
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .weight(weight)
                .height(height)
                .build();
    }
}
