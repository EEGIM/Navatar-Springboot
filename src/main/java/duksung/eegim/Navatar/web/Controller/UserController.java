package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.User.Role;
import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/users/signup")
    public String getUser(Model model, @SessionAttribute("user") SessionUser user){
        model.addAttribute("email", user.getEmail());
        model.addAttribute("name", user.getName());
        return "signup";
    }

    @PostMapping("/users/signup")
    public String addUser(UserRegisterDto userRegisterDto, @SessionAttribute("user") SessionUser user){ // HttpServletRequest request
        // System.out.println("이메일"+userService.userUpdate(user.getEmail(), userRegisterDto));
        userService.userUpdate(user.getEmail(), userRegisterDto);
        return "signup-after";
    }

    @GetMapping("/users/modify")
    public String UserInfo(Model model, @SessionAttribute("user") SessionUser user){
        // 로그인 제대로 안했을 때, 다시 세션 만료 방법 찾기
        User u = userService.getUser(user.getEmail());
        if (u.getRole() == Role.GUEST)
            httpSession.invalidate();
        model.addAttribute("user", u);
        return "user-modify";
    }

    @PostMapping("/users/modify")
    public String UserModify(UserRegisterDto userRegisterDto, @SessionAttribute("user") SessionUser user){
        userService.userUpdate(user.getEmail(), userRegisterDto);
        return "redirect:mypage";
    }

    @GetMapping("/users/signin")
    public String signin(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "signin";
    }

    @GetMapping("/users/mypage")
    public String mypage(Model model, @SessionAttribute("user") SessionUser user){
        model.addAttribute("userName", user.getName());
        model.addAttribute("userPicture", user.getPicture());
        return "mypage";
    }

    @GetMapping("/foryou")
    public String recommandProduct(Model model, @SessionAttribute("user") SessionUser user){
        HashMap<String, Long> info = userService.getSizeInfo(user.getEmail());
        model.addAttribute("products", userService.getSizeRecommand(info));
        model.addAttribute("userSize", info);
        model.addAttribute("userName", user.getName());
        return "foryou";
    }

}
