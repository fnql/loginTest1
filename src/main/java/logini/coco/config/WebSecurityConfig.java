package logini.coco.config;

import logini.coco.service.CustomOAuth2UserService;
import logini.coco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/templates/menu.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
            http
                    .authorizeRequests()
                        .antMatchers("/login", "/signup", "/main","/ajaxTest","/menu").permitAll()
                        .antMatchers("/").hasRole("USER")
                        .antMatchers("/admin").hasRole("ADMIN")

//                        .anyRequest().authenticated()
//                    .and()
//                        .formLogin()
//                            .loginPage("/login")
//                            .defaultSuccessUrl("/")
                    .and()
                        .logout()
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true)
                    .and()
                        .oauth2Login()
                            .userInfoEndpoint() // oauth2 로그인 성공 후 가져올 때의 설정들
                                     // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                                .userService(customOAuth2UserService); // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
