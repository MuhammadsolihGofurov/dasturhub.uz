package dasturhub.uz.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {


    @GetMapping
    public String getMainIndexPage(Model model) {
        //
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {

        return "about";
    }


}
