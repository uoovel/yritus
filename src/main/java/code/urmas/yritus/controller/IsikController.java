package code.urmas.yritus.controller;

import code.urmas.yritus.model.Isik;
import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.IsikService;
import code.urmas.yritus.service.OsalusService;
import code.urmas.yritus.service.YritusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IsikController {

    @Autowired
    private YritusService yritusService;
    @Autowired
    private IsikService isikService;

    @Autowired
    private OsalusService osalusService;

    @RequestMapping("/vormOsaleja/{id}")
    public String showOsalejadForm(@PathVariable(name = "id") int id, Model model) {
        Yritus yritus = yritusService.get(id);
        Isik isik = new Isik();

        Osalus osalus = new Osalus();
        osalus.setYritus(yritus);
        osalus.setIsik(isik);


        model.addAttribute("osalus", osalus);
        //model.addAttribute("isik", isik);


        return "vormOsaleja.html";
    }

    @RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("osalus") Osalus osalus, BindingResult result) {
        if(result.hasErrors()) {
            return "vormOsaleja.html";
        }

        Isik isikSaved = isikService.saveCustomer(osalus.getIsik());

        Long yritusId = osalus.getYritus().getId();

        Yritus yritus = yritusService.get(yritusId);

        Osalus osalusSalvestatav = new Osalus();
        osalusSalvestatav.setYritus(yritus);
        osalusSalvestatav.setIsik(isikSaved);

        osalusService.saveOsalus(osalusSalvestatav);

        return "redirect:/";
    }



}
