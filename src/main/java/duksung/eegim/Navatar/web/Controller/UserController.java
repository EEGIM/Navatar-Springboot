package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.User.Cart;
import duksung.eegim.Navatar.web.dto.CartDto;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;
    private String name = "";
    private String email = "";

    @GetMapping("/users/signup")
    public String getUsersList(Model model){
//        HttpSession httpSession = request.getSession(); // 여기 service로 가는게 맞나?
//        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
//        email = sessionUser.getEmail();
//        name = sessionUser.getName();
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "signup";
    }

    // 유저 내용은 별도의 컨트롤러와 서비스 만들어서 넣기. (관리)
    // 예외 처리 하기
    // dto 만들어서 처리하기 (직접 처리 말고) + jquery도 사용하기
    @PostMapping("/users/signup")
    public String addUser(UserRegisterDto userRegisterDto){ // HttpServletRequest request
//        HttpSession httpSession = request.getSession(); // 여기 service로 가는게 맞나?
//        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
//        email = sessionUser.getEmail();
        System.out.println("이메일"+userService.userUpdate(email, userRegisterDto));
        return "signup-after";
    }

    @GetMapping("/users/modify")
    public String UserInfo(Model model){
        model.addAttribute("user", userService.getUser(email));
        return "user-modify";
    }

    @PostMapping("/users/modify")
    public String UserModify(UserRegisterDto userRegisterDto){
        userService.userUpdate(email, userRegisterDto);
        return "redirect:mypage";
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
            email = user.getEmail();
            name = user.getName();
            model.addAttribute("userName", user.getName());
        }

        return "signin";
    }

    @GetMapping("/users/mypage")
    public String mypage(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            email = user.getEmail();
            name = user.getName();
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("userName", name);
        return "mypage";
    }

    @GetMapping("/users/like")
    public String userLike(Model model){
        model.addAttribute("productList", userService.getLikeList(email));
        return "mypage-like";
    }

    @PostMapping("/products/{productNo}/like") // 찜 등록
    public String likeAdd(@PathVariable Long productNo){
        userService.addLike(productNo, getUserSession().getEmail());
        return "redirect:/products/{productNo}";
    }

    @GetMapping("/users/cart")
    public String userCart(Model model){
        List<Cart> cartList = userService.getCart(getUserSession().getEmail());
        List<Product> products =userService.getCartList(cartList);
        model.addAttribute("productList", products);
        // model.addAttribute("cartList", cartList); // 장바구니도 그냥 찜처럼 이 안에 담고 있는게 낫겠다.
        model.addAttribute("price", userService.getPrice(products));
        return "mypage-cart";
    }

    @PostMapping("/products/{productNo}/cart")
    public String AddToCart(CartDto requestDto){
        userService.addCart(requestDto, getUserSession().getEmail());
        return "redirect:/products/{productNo}";
    }

    private SessionUser getUserSession(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return user;
    }
}
