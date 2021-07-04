package logini.coco.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){

        String password = "1111";

        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw = " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult = " + matchResult);
    }
}


        oooooo   oooooo     oooo   .oooooo.     .oooooo.     .oooooo.    ooooo
        `888.    `888.     .8'   d8P'  `Y8b   d8P'  `Y8b   d8P'  `Y8b   `888'
        `888.   .8888.   .8'   888      888 888      888 888            888
        `888  .8'`888. .8'    888      888 888      888 888            888
        `888.8'  `888.8'     888      888 888      888 888     ooooo  888
        `888'    `888'      `88b    d88' `88b    d88' `88.    .88'   888
        `8'      `8'        `Y8bood8P'   `Y8bood8P'   `Y8bood8P'   o888o



        I8,        8        ,8I   ,ad8888ba,      ,ad8888ba,      ,ad8888ba,   88
         `8b       d8b       d8'  d8"'    `"8b    d8"'    `"8b    d8"'    `"8b  88
          "8,     ,8"8,     ,8"  d8'        `8b  d8'        `8b  d8'            88
           Y8     8P Y8     8P   88          88  88          88  88             88
            `8b   d8' `8b   d8'   88          88  88          88  88      88888  88
             `8a a8'   `8a a8'    Y8,        ,8P  Y8,        ,8P  Y8,        88  88
        `8a8'     `8a8'      Y8a.    .a8P    Y8a.    .a8P    Y8a.    .a88  88
        `8'       `8'        `"Y8888Y"'      `"Y8888Y"'      `"Y88888P"   88





