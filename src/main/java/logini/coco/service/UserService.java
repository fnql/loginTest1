package logini.coco.service;

import logini.coco.dto.UserInfoDto;
import logini.coco.entity.coMember;
import logini.coco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    public boolean overlap;
    private final UserRepository userRepository;

    @Override
    public coMember loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public List<coMember> findAllDesc(){
        return userRepository.findAll();
    }

    //회원 정보 저장 return 회원의 PK
    public String save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(coMember.builder()
            .email(infoDto.getEmail())
            .auth(infoDto.getAuth())
            .addr(infoDto.getAddr())
            .hobby(infoDto.getHobby())
            .name(infoDto.getName())
            .password(infoDto.getPassword()).build()).getEmail();
    }

    public int userIdCheck(String user_id) {
        System.out.println("user_id = " + user_id);
        return (userRepository.existsByName(user_id))? 1 :0 ;
    }

    //TODO: kakaoIdCheck 만들기 리턴값은 필요없을듯
}
