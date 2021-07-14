package logini.coco.config;

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

    private final UserService userService;

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/css/main.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
            http
                    .authorizeRequests()
                        .antMatchers("/login", "/signup", "/user").permitAll()
                        .antMatchers("/").hasRole("USER")
                        .antMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                    .and()
                        .logout()
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
