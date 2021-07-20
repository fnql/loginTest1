package logini.coco.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// noArgs =파라미터 없는 기본 생성자 생성 + 접근 제어 protected
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class coMember implements UserDetails {

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

    @Column(name = "auth")
    private String auth;

    @Builder
    public coMember(String email, String name, String password, String addr, String hobby, String auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.addr =addr;
        this.hobby = hobby;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername(){
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }
}
