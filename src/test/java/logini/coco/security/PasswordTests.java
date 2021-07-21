package logini.coco.security;

import logini.coco.entity.coMember;
import logini.coco.repository.UserRepository;
import logini.coco.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){

        String password = "1234";

        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw = " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult = " + matchResult);
    }


    @Test
    public void saveTest(){
        //coMember member = new coMember("dd", "fn", "1234", "고척동","game", "admin");

        final coMember member = coMember.builder()
                .email("dd")
                .addr("고척")
                .auth("admin")
                .name("fnfn")
                .password("1234")
                .hobby("game")
                .build();
        assertThat(member.getUsername()).isEqualTo("dd");
        System.out.println("member = " + member.getPassword());
    }
}


