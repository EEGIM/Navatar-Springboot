package duksung.eegim.Navatar.domain.User;

import duksung.eegim.Navatar.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="User")
public class User extends TimeEntity {
    //userno id password name email weight height

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userno;

    @Column(name="name", length=45)
    private String name;

    @Column(name="email", length=50)
    private String email;

    @Column(name="weight", columnDefinition = "INT")
    private Long weight;

    @Column(name="height", columnDefinition = "INT")
    private Long height;

    @Column(name="isdeleted", columnDefinition = "tinyint")
    private int isdeleted = 0;

    @Enumerated(EnumType.STRING)
    @Column(name="role", length=45)
    private Role role;

    @Builder
    public User(Long userno, String name, String email, Long weight, Long height, Role role){
        this.userno = userno;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.role = role;
    }

    public User update(String name){ // 리소스 서버에서의 정보 변경
        this.name = name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
