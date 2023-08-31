package code.urmas.yritus.controller;

import code.urmas.yritus.model.Isik;
import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.OsalusService;
import code.urmas.yritus.service.YritusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AvalehtController {

    @Autowired
    private YritusService yritusService;
    @Autowired
    private OsalusService osalusService;

    @RequestMapping("/")
    public String avaleht(Model model){
        List<Yritus> listYritused = yritusService.listAll();
        model.addAttribute("listYritused", listYritused);
        return "index.html";
    }

    @RequestMapping("/koikOsalejad/{id}")
    public String koikOsalejad(@PathVariable(name = "id") int id, Model model){
        Yritus yritus = yritusService.get(id);
        List<Osalus> osalusList = osalusService.getOsalussByYritus(yritus);
        model.addAttribute("listOsalus", osalusList);
        return "koikOsalejad.html";
    }

}
