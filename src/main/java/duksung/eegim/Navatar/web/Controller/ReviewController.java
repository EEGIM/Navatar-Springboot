package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.Cart.Cart;
import duksung.eegim.Navatar.web.dto.SatisfactionReviewDto;
import duksung.eegim.Navatar.web.service.CartService;
import duksung.eegim.Navatar.web.service.ReviewService;
import duksung.eegim.Navatar.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final UserService userService;
    private final CartService cartService;
    private final ReviewService reviewService;

    @GetMapping("/users/review")
    public String userReview(Model model, @SessionAttribute("user") SessionUser user){
        List<Cart> cartList = cartService.getCartByUserNo(userService.getUserNo(user.getEmail()));
        model.addAttribute("cartList", cartList);
        return "mypagereview";
    }

    @GetMapping("/reviews/{cartNo}/{productNo}")
    public String ReviewPage(Model model, @PathVariable Long cartNo, @PathVariable Long productNo, @SessionAttribute("user") SessionUser user){
        model.addAttribute("review", reviewService.getReviewByCartNo(cartNo));
        model.addAttribute("user", userService.getUser(user.getEmail()));
        model.addAttribute("cart", cartService.getCartByCartNo(cartNo));
        return "review";
    }

    @PostMapping("/reviews/{cartNo}/{productNo}")
    public String WriteReview(SatisfactionReviewDto satisfactionReviewDto){
        reviewService.writeReview(satisfactionReviewDto);
        return "redirect:/users/review";
    }

    @GetMapping("/reviews/{reviewNo}")
    public String getReviewDetail(Model model, @PathVariable Long reviewNo){
        model.addAttribute("review", reviewService.getReviewByReviewNo(reviewNo));
        return "review-detail";
    }

}
