package duksung.eegim.Navatar.domain.User;

import duksung.eegim.Navatar.domain.TimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="User")
public class User extends TimeEntity {
    //userno id password name email weight height

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userno;

    @Column(name="id", length=45)
    private String id;

    @Column(name="password", length=45)
    private String password;

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

    @Builder
    public User(Long userno, String id, String password, String name, String email, Long weight, Long height){
        this.userno = userno;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
    }
}
