package logini.coco.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ApiController {

    @Value("${kakao.js}")
    String kakaoApi;

    @GetMapping("/getKakaoApi")
    public String getKakaoApi()  {
        System.out.println("getKakaoApi");
        return kakaoApi;
    }

}
