package logini.coco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import logini.coco.dto.MailDto;
import logini.coco.dto.UserInfoDto;
import logini.coco.service.MailService;
import logini.coco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static logini.coco.service.MailService.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //get으로 받으면 오류나요~
    @PostMapping("/main")
    public String signup(UserInfoDto infoDto) {
        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @RequestMapping(value = "/user/idCheck", method = RequestMethod.GET)
    @ResponseBody
    public int idCheck(@RequestParam("userId") String user_id) {
        return userService.userIdCheck(user_id);
    }

    //이메일 인증
    @RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception {
        /* 뷰(View)로부터 넘어온 데이터 확인 */
        logger.info("이메일 데이터 전송 확인");
        logger.info("인증번호 : " + email);

        /* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        logger.info("인증번호 " + checkNum);

        /* 이메일 보내기 */
        String setFrom = "00woogi@gmail.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + checkNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        String num = Integer.toString(checkNum);
        return num;
    }


}