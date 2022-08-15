package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/users/signup")
    public String getUsersList(){
        return "signup";
    }

    // 유저 내용은 별도의 컨트롤러와 서비스 만들어서 넣기. (관리)
    // 예외 처리 하기
    // dto 만들어서 처리하기 (직접 처리 말고) + jquery도 사용하기
    @PostMapping("/users/signup")
    public String addUser(UserRegisterDto userRegisterDto){
        userService.userSave(userRegisterDto);
        return "signup-after";
    }

    @GetMapping("/users/{userno}/delete")
    public String getUser(Model model, @PathVariable long userno){
        model.addAttribute("user", userService.getUser(userno));
        return "users";
    }

    @PostMapping("/users/{userno}/delete")
    public String deleteUser(Model model, @PathVariable long userno){
        userService.deleteUser(userno);
        return "users";
    }

    @GetMapping("/users/signin")
    public String signin(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "signin";
    }
}
