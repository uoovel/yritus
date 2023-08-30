package code.urmas.yritus.controller;

import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.YritusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class YritusController {
    @Autowired
    private YritusService yritusService;

    @RequestMapping("/newevent")
    public String uusyritus(Model model){
        Yritus yritus = new Yritus();
        model.addAttribute("yritus", yritus);
        return "new_event.html";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String salvestaYritus(
            @ModelAttribute("yritus") Yritus yritus
    ){
        yritusService.save(yritus);
        return "redirect:/";
    }


}
