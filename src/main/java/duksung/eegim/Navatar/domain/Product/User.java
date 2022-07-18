package duksung.eegim.Navatar.domain.Product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    //userno id password name email weight height

    @Id
    @Column(name="userno", columnDefinition = "INT")
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
}
