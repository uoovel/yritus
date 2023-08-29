package code.urmas.yritus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AvalehtController {

    @RequestMapping("/")
    public String avaleht(Model model){
        return "index.html";
    }

}
