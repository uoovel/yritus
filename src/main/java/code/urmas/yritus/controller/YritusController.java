package code.urmas.yritus.controller;

import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.OsalusService;
import code.urmas.yritus.service.YritusService;
import code.urmas.yritus.service.dto.YritusDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        YritusDto yritus = new YritusDto();
        model.addAttribute("yritus", yritus);
        return "new_event.html";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String salvestaYritus(
            @Valid @ModelAttribute("yritus") YritusDto yritusDto,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("yritus", yritusDto);
            return "new_event.html";
        }
        String yrituseAeg = yritusDto.getAeg();
        String kuupaev = yrituseAeg.substring(0,10);
        String kell = yrituseAeg.substring(11,16);
        String yrituseAeg2 = kuupaev + " " + kell + ":00";
        System.out.println("YritusController100: " + yrituseAeg);
        System.out.println("YritusController200: " + yrituseAeg2);
        Timestamp aegts = Timestamp.valueOf(yrituseAeg2);
        Yritus yritus = new Yritus();
        yritus.setNimetus(yritusDto.getNimetus());
        yritus.setAegts(aegts);
        yritus.setKoht(yritusDto.getKoht());
        yritus.setLisainfo(yritusDto.getLisainfo());
        yritus.setAeg(yritusDto.getAeg());
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
