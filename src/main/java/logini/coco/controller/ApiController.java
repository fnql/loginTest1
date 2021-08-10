package logini.coco.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ApiController {

    @Value("${kakao.js}")
    String kakaoApi;

    @RequestMapping(value = "/getKakaoApi", method = RequestMethod.POST )
    public String getKakaoApi()  {
        System.out.println("getKakaoApi");
        return kakaoApi;
    }

}
