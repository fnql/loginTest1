package logini.coco.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(coMember user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
