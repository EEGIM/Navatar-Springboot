package duksung.eegim.Navatar.web.Controller;

import duksung.eegim.Navatar.web.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;
    private Long height = 0L;
    private Long weight = 0L;
    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping("/products/{productNo}")
    public String getProduct(Model model, @PathVariable String productNo){

        Long pno = Long.parseLong(productNo);
        // model.addAttribute() 여러개로 사용하는 것, 의존성에 문제?
        model.addAttribute("product", productService.getProduct(pno));
        model.addAttribute("details", productService.getProductDetail(pno));
        model.addAttribute("sizes", productService.getSize(pno));
        model.addAttribute("reviews", productService.getReviews(pno));
        model.addAttribute("satisfactions", productService.getProductSatisfaction(pno, height, weight));
        if (height == 0L || weight == 0L){
            model.addAttribute("sizevalue", "전체 체형");
        }
        else {{
            model.addAttribute("heightvalue", height);
            model.addAttribute("weightvalue", weight);
        }}

        height = 0L;
        weight = 0L;

        return "product";
    }

    @RequestMapping(value="/findsize/{productNo}", method = {RequestMethod.GET, RequestMethod.POST})
    public String postProductSize(@PathVariable String productNo, @RequestParam Long height, @RequestParam Long weight){
        this.height = height;
        this.weight = weight;
        return "redirect:/products/"+productNo+"#size-satisfaction";
    }


    @GetMapping("/brands/{brand}")
    public String getProductByBrand(Model model, @PathVariable String brand){
        model.addAttribute("product", productService.getListByBrand(brand));
        return brand;
    }

    @GetMapping("/brands/{brand}/{cate}")
    public String getProductByBrandNCate(Model model, @PathVariable String brand, @PathVariable String cate){
        model.addAttribute("product", productService.getListByBrandNCate(brand, cate));
        return brand;
    }

    // arfitting
    @GetMapping("/arfitting/{productNo}")
    public String ARFitting(){
        return "/html/arfitting.html";
    }
}
