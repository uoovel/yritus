package code.urmas.yritus.controller;

import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.YritusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AvalehtController {

    @Autowired
    private YritusService yritusService;

    @RequestMapping("/")
    public String avaleht(Model model){
        List<Yritus> listYritused = yritusService.listAll();
        model.addAttribute("listYritused", listYritused);
        return "index.html";
    }

}
