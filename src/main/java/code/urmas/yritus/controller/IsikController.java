package code.urmas.yritus.controller;

import code.urmas.yritus.model.*;
import code.urmas.yritus.service.*;
import code.urmas.yritus.service.dto.OsalusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class IsikController {

    @Autowired
    private YritusService yritusService;
    @Autowired
    private IsikService isikService;
    @Autowired
    private OsalusService osalusService;
    @Autowired
    private TyypService tyypService;
    @Autowired
    private EraisikService eraisikService;
    @Autowired
    private EttevoteService ettevoteService;
    @Autowired
    private MakseviisService makseviisService;

    @RequestMapping("/vormOsaleja/{id}")
    public String showOsalejadForm(@PathVariable(name = "id") int id, Model model) {
        Yritus yritus = yritusService.get(id);


        OsalusDto osalusDto = new OsalusDto();

        osalusDto.setYritus(yritus);

        //Isik isik = new Isik();
        //Tyyp tyyp = new Tyyp();
        //isik.setTyyp(tyyp);
        //osalus.setIsik(isik);
        //Eraisik


        model.addAttribute("osalus", osalusDto);
        //model.addAttribute("isik", isik);

        List<Tyyp> listTyyp = tyypService.listAll();
        model.addAttribute("listTyyp" ,listTyyp);

        List<Makseviis> listMakseviis = makseviisService.listAll();
        model.addAttribute("makseviisList" ,listMakseviis);

        return "vormOsaleja.html";
    }

    @RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("osalus") OsalusDto osalus, BindingResult result) {
        if(result.hasErrors()) {
            return "vormOsaleja.html";
        }



        Isik isik = osalus.getIsik();
        Isik isikSaved = null;
        Integer osalejateArv = null;


        if(isik.getTyyp().getId() == 1){
            Eraisik eraisik = osalus.getEraisik();
            isik.setNimi(eraisik.getEesnimi() + " " + eraisik.getPerekonnanimi());
            isikSaved = isikService.saveCustomer(isik);
            eraisik.setIsik(isikSaved);
            Eraisik savedEraisik = eraisikService.saveEraisik(eraisik);
            osalejateArv = 1;
        }
        if(isik.getTyyp().getId() == 2){
            Ettevote ettevote = osalus.getEttevote();
            isik.setNimi(ettevote.getJuriidilinenimi());
            isikSaved = isikService.saveCustomer(isik);
            ettevote.setIsik(isikSaved);
            Ettevote savedEttevote = ettevoteService.saveEttevote(ettevote);
            osalejateArv = osalus.getTulijatearv();
        }



        Long yritusId = osalus.getYritus().getId();

        Yritus yritus = yritusService.get(yritusId);
        Makseviis makseviis = makseviisService.get(osalus.getMakseviis().getId());



        String lisainfo = osalus.getLisainfo();

        Osalus osalusSalvestatav = new Osalus();
        osalusSalvestatav.setYritus(yritus);
        osalusSalvestatav.setIsik(isikSaved);
        osalusSalvestatav.setMakseviis(makseviis);
        osalusSalvestatav.setTulijatearv(osalejateArv);
        osalusSalvestatav.setLisainfo(lisainfo);

        osalusService.saveOsalus(osalusSalvestatav);

        return "redirect:/";
    }

    @RequestMapping("/vaataIsikut/{id}")
    public String showIsikDetailForm(@PathVariable(name = "id") int id, Model model) {
        Isik isik = isikService.get(id);
        int tyypId = (isik.getTyyp().getId()).intValue();

        if(tyypId == 1){
            Eraisik eraisik = eraisikService.getByIsik(isik);
            eraisik.setIsik(isik);
            model.addAttribute("eraisik", eraisik);
            return "eraisikDetail.html";
        }else{
            Ettevote ettevote = ettevoteService.getByIsik(isik);
            ettevote.setIsik(isik);
            model.addAttribute("ettevote", ettevote);
            return "ettevoteDetail.html";
        }
    }

    @RequestMapping(value = "/editeraisik", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("eraisik") Eraisik eraisik, BindingResult result) {

        eraisikService.saveEraisik(eraisik);
        Isik isik = eraisik.getIsik();
        isik.setNimi(eraisik.getEesnimi());
        isikService.saveCustomer(isik);

        return "redirect:/";
    }

    @RequestMapping(value = "/editettevote", method = RequestMethod.POST)
    public String editFirma(@ModelAttribute("ettevote") Ettevote ettevote, BindingResult result) {

        ettevoteService.saveEttevote(ettevote);
        Isik isik = ettevote.getIsik();
        isik.setNimi(ettevote.getJuriidilinenimi());
        isikService.saveCustomer(isik);

        return "redirect:/";
    }



}
