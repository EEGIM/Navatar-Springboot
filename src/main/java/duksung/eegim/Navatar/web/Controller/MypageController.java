package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequiredArgsConstructor
@Controller
@SessionAttributes("user")
public class MypageController {

    // 삭제!!

    private final UserService userService;


    @GetMapping("/html/dddd")
    public void mmm(@SessionAttribute("user") SessionUser user){
        System.out.println("세션확인: "+user.getName());
    }

    @GetMapping("/html/aaaa")
    public void mmmm(@ModelAttribute("user") SessionUser user){
        System.out.println(user.getEmail());
    }

}
