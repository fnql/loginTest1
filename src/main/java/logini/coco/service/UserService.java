package logini.coco.service;

import logini.coco.dto.UserInfoDto;
import logini.coco.entity.UserInfo;
import logini.coco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    //회원 정보 저장 return 회원의 PK
    public String save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
            .email(infoDto.getEmail())
            .auth(infoDto.getAuth())
            .addr(infoDto.getAddr())
            .hobby(infoDto.getHobby())
            .name(infoDto.getName())
            .password(infoDto.getPassword()).build()).getEmail();
    }
}
