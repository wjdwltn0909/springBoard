package com.nc13.springBoard.controller;

import com.nc13.springBoard.model.UserDTO;
import com.nc13.springBoard.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// http://localhost:8080/user/~~~ : 이건 다 UserController로 불러오고 싶음
@RequestMapping("/user/")
public class UserController {
    // 실제 SQL 통신을 담당할 Service 객체
    @Autowired
    private UserService userService;

    // 사용자가 로그인을 할 시 실행할
    // auth 메소드
    @PostMapping("auth")
    // POST 혹은 GET 방식으로 웹페이지의 값을 받아올 때에는
    // 파라미터에 해당 form의 name 어트리뷰트와 같은 이름을 가진
    // 파라미터를 적어주면 된다.
    // 또한, 해당 name 어트리뷰트를 가진 클래스 객체를 파라미터로 잡아주면
    // 자동으로 데이터가 바인딩 된다. *** 데이터까지 담당하면 단일책임원칙에서 어긋난다. -> service 파일 만드는 이유
    public String auth(UserDTO userDTO, HttpSession session) {
        UserDTO result = userService.auth(userDTO);
        if (result != null) {
            session.setAttribute("logIn", result);
        }

        // 만약 우리가 해당 메소드를 실행시키고 나서 특정 URL로 이동시킬 때에는 다음과 같이 적어준다.
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegister(){
        return "user/register";
    }

    @PostMapping("register")
    public String register(UserDTO userDTO) {
        System.out.println(userDTO);

        return "redirect:/";
    }
}
