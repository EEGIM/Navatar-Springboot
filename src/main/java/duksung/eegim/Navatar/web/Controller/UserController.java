package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

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
        model.addAttribute("user", userService.getUser(user.getEmail()));
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
        model.addAttribute("products", userService.getSizeRecommand(user.getEmail()));
        model.addAttribute("userName", user.getName());
        return "foryou";
    }

}
