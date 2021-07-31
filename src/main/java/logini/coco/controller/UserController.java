package logini.coco.controller;

import logini.coco.dto.MailDto;
import logini.coco.dto.UserInfoDto;
import logini.coco.service.MailService;
import logini.coco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Random;

import static logini.coco.service.MailService.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    //get으로 받으면 오류나요~
    @PostMapping("/main")
    public String signup(UserInfoDto infoDto){
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
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public void mailCheckGET(String email) throws Exception{
        /* 뷰(View)로부터 넘어온 데이터 확인 */
        logger.info("이메일 데이터 전송 확인");
        logger.info("인증번호 : " + email);

    }
}
