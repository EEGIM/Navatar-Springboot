package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.domain.Product.User;
import duksung.eegim.Navatar.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/users/signup")
    public String getUsersList(Model model){
        model.addAttribute("user", userService.getUsers());
        return "signup";
    }

    // 유저 내용은 별도의 컨트롤러와 서비스 만들어서 넣기. (관리)
    // 예외 처리 하기
    // dto 만들어서 처리하기 (직접 처리 말고) + jquery도 사용하기
    @PostMapping("/users/signup")
    public String addUser(Model model, @ModelAttribute("user") User user){
        User newUser = userService.userSave(user);
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
}
