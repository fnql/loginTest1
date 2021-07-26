package logini.coco.controller;

import logini.coco.dto.MailDto;
import logini.coco.dto.UserInfoDto;
import logini.coco.service.MailService;
import logini.coco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {

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

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(MailDto mailDto) {
        MailService.mailSend(mailDto);
    }
}
