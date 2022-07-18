package duksung.eegim.Navatar.web;

import duksung.eegim.Navatar.domain.Product.User;
import duksung.eegim.Navatar.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private final ProductService productService;

    public IndexController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("product", productService.getList());
        return "index";
    }

}
