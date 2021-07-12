package logini.coco.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

// noArgs =파라미터 없는 기본 생성자 생성 + 접근 제어 protected
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserInfo implements UserDetails {

    @Id
    @Column(name = "email")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 자동생성
    private String email;

    @Column(name = "name", unique = true)
    public String name;

    @Column(name = "password")
    private String password;

    @Column(name = "addr")
    public String addr;

    @Column(name = "hobby")
    public String hobby;

    @Builder
    public UserInfo(String email,String name, String password, String addr, String hobby) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.addr =addr;
        this.hobby = hobby;
    }
}
