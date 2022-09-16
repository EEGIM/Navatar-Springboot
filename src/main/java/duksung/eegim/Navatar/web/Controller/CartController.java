package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.Cart.Cart;
import duksung.eegim.Navatar.web.dto.CartDto;
import duksung.eegim.Navatar.web.service.CartService;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/users/cart")
    public String userCart(Model model, @SessionAttribute("user") SessionUser user){
        List<Cart> cartList = cartService.getCartByUserNo(userService.getUserNo(user.getEmail()));
        model.addAttribute("cartList", cartList);
        // model.addAttribute("cartList", cartList); // 장바구니도 그냥 찜처럼 이 안에 담고 있는게 낫겠다.
        model.addAttribute("price", userService.getPrice(cartList.stream()
                .map(entity -> entity.getProduct()).collect(Collectors.toList())));
        return "mypage-cart";
    }

    @PostMapping("/products/{productNo}/cart")
    public String AddToCart(CartDto requestDto, @PathVariable Long productNo, @SessionAttribute("user") SessionUser user){
        cartService.addCart(requestDto, userService.getUserNo(user.getEmail()), productNo);
        return "redirect:/products/{productNo}";
    }

    @PostMapping("/users/cart/{cartNo}/delete")
    public String DeleteFromCart(@PathVariable Long cartNo){
        cartService.deleteFromCart(cartNo);
        return "redirect:/users/cart";
    }

}
