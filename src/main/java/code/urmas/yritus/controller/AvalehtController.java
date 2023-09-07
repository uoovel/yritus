package code.urmas.yritus.controller;

import code.urmas.yritus.model.Isik;
import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Tyyp;
import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.service.OsalusService;
import code.urmas.yritus.service.StartService;
import code.urmas.yritus.service.TyypService;
import code.urmas.yritus.service.YritusService;
import code.urmas.yritus.dto.YritusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AvalehtController {

    @Autowired
    private YritusService yritusService;
    @Autowired
    private OsalusService osalusService;

    @Autowired
    private StartService startService;
    @Autowired
    private TyypService tyypService;

    @RequestMapping("/")
    public String avaleht(Model model){
        List<Tyyp> tyypList = tyypService.listAll();
        if(tyypList.isEmpty()){
            startService.lisaAlgandmed();
        }

        List<Yritus> listYritused = yritusService.listAll();

        List<YritusDto> listYritusDtoTulevik = new ArrayList<>();
        List<YritusDto> listYritusDtoMinevik = new ArrayList<>();
        Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now());
        Long nowLong = timestampNow.getTime();
        //System.out.println("AvalehtController100: " + dateTime);
        //Timestamp aegPraegu =

        for (int i = 0; i < listYritused.size(); i++) {
            Yritus yritus = listYritused.get(i);
            Timestamp yrituseAeg = yritus.getAegts();
            YritusDto yritusDto = new YritusDto();
            yritusDto.setId(yritus.getId());
            yritusDto.setNimetus(yritus.getNimetus());
            yritusDto.setAeg(yritus.getAeg());
            yritusDto.setKoht(yritus.getKoht());
            yritusDto.setLisainfo(yritus.getLisainfo());
            yritusDto.setAegts(yritus.getAegts());
            try{
                yritusDto.setOsalejaid(osalusService.summOsalejad(yritus));
            }catch (Exception n){
                yritusDto.setOsalejaid(0);
            }


            if(yrituseAeg.getTime() > nowLong){
                System.out.println("AvalehtController200: " + yrituseAeg);
                listYritusDtoTulevik.add(yritusDto);
            } else {
                listYritusDtoMinevik.add(yritusDto);
            }

        }
        model.addAttribute("listYritusMinevik", listYritusDtoMinevik);

        model.addAttribute("listYritusTulevik", listYritusDtoTulevik);
        return "index.html";
    }

    @RequestMapping("/koikOsalejad/{id}")
    public String koikOsalejad(@PathVariable(name = "id") int id, Model model){
        System.out.println(id);
        Yritus yritus = yritusService.get(id);
        List<Osalus> osalusList = osalusService.getOsalussByYritus(yritus);
        model.addAttribute("listOsalus", osalusList);
        model.addAttribute("yritus", yritus);
        return "koikOsalejad.html";
    }

}
