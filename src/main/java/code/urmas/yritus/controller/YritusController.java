package code.urmas.yritus.controller;

import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.OsalusService;
import code.urmas.yritus.service.YritusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class YritusController {
    @Autowired
    private YritusService yritusService;
    @Autowired
    private OsalusService osalusService;

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
        String yrituseAeg = yritus.getAeg();
        String kuupaev = yrituseAeg.substring(0,10);
        String kell = yrituseAeg.substring(11,16);
        String yrituseAeg2 = kuupaev + " " + kell + ":00";
        System.out.println("YritusController100: " + yrituseAeg);
        System.out.println("YritusController200: " + yrituseAeg2);
        Timestamp aegts = Timestamp.valueOf(yrituseAeg2);
        yritus.setAegts(aegts);
        yritusService.save(yritus);
        return "redirect:/";
    }
    @RequestMapping("/deleteYritus/{id}")
    public String deleteYritus(@PathVariable(name = "id") Long id) {
        Yritus yritus = yritusService.get(id);
        osalusService.deleteAllByYritus(yritus);
        yritusService.delete(id);
        return "redirect:/";
    }


}
