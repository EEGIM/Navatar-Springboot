package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.web.service.LikesService;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@Controller
public class LikesController {

    private final UserService userService;
    private final LikesService likesService;

    @GetMapping("/users/like")
    public String userLike(Model model, @SessionAttribute("user") SessionUser user){

        model.addAttribute("productList", likesService.getLikeList(userService.getUserNo(user.getEmail())));
        return "mypage-like";
    }

    @PostMapping(value = "/products/{productNo}/like") // 찜 등록
    public String likeAdd(@PathVariable Long productNo, @SessionAttribute("user") SessionUser user){
        likesService.addLike(productNo, userService.getUserNo(user.getEmail()));
        return "redirect:/products/{productNo}";
    }

}
