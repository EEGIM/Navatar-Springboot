package duksung.eegim.Navatar.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/deploy-test")
    public String deployTest() {return "AWS-Jenkins deploy test!!!!";}
}
