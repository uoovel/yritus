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

        Isik isik = new Isik();
        //isik.setId(Long.valueOf(-1));
        osalusDto.setIsik(isik);
        //Eraisik
        Tyyp tyyp = tyypService.getById(Long.valueOf(1));
        List<Isik> listIsikEra = isikService.listByTyyp(tyyp);
        model.addAttribute("isikListEra" ,listIsikEra);
        tyyp = tyypService.getById(Long.valueOf(2));
        List<Isik> listIsikEtt = isikService.listByTyyp(tyyp);
        model.addAttribute("isikListEtt" ,listIsikEtt);

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
        Long eraisikId = osalus.getEraisik().getIsik().getId();
        Long ettevoteId = osalus.getEttevote().getIsik().getId();
        Isik isik = osalus.getIsik();
        Isik isikSaved = null;
        Boolean olemasolev = false;
        if(eraisikId != 0){
            isik = isikService.get(eraisikId);
            isikSaved = isik;
            olemasolev = true;
        }
        if(ettevoteId != 0){
            isik = isikService.get(ettevoteId);
            isikSaved = isik;
            olemasolev = true;
        }




        Integer osalejateArv = null;


        if(isik.getTyyp().getId() == 1 && !olemasolev){
            Eraisik eraisik = osalus.getEraisik();
            isik.setNimi(eraisik.getEesnimi() + " " + eraisik.getPerekonnanimi());
            isik.setKood(eraisik.getIsikukood());
            isikSaved = isikService.saveCustomer(isik);
            eraisik.setIsik(isikSaved);
            Eraisik savedEraisik = eraisikService.saveEraisik(eraisik);

        }
        if(isik.getTyyp().getId() == 2 && !olemasolev){
            System.out.println("saveCustomer: " + isik.getId());
            Ettevote ettevote = osalus.getEttevote();
            isik.setNimi(ettevote.getJuriidilinenimi());
            isik.setKood(ettevote.getRegistrikood());
            isikSaved = isikService.saveCustomer(isik);
            ettevote.setIsik(isikSaved);
            Ettevote savedEttevote = ettevoteService.saveEttevote(ettevote);

        }

        if(isik.getTyyp().getId() == 1){
            osalejateArv = 1;
        }
        if(isik.getTyyp().getId() == 2){
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

    @RequestMapping("/vaataOsalust/{id}")
    public String showIsikDetailForm(@PathVariable(name = "id") long id, Model model) {
        Osalus osalus = osalusService.get(id);
        Isik isik = osalus.getIsik();

        int tyypId = (isik.getTyyp().getId()).intValue();
        OsalusDto osalusDto = new OsalusDto();
        osalusDto.setId(id);
        osalusDto.setTulijatearv(osalus.getTulijatearv());
        osalusDto.setMakseviis(osalus.getMakseviis());
        osalusDto.setLisainfo(osalus.getLisainfo());
        List<Makseviis> listMakseviis = makseviisService.listAll();
        model.addAttribute("makseviisList" ,listMakseviis);
        if(tyypId == 1){
            Eraisik eraisik = eraisikService.getByIsik(isik);
            osalusDto.setEraisik(eraisik);
            model.addAttribute("osalus", osalusDto);
            return "eraisikDetail.html";
        }else{
            Ettevote ettevote = ettevoteService.getByIsik(isik);
            osalusDto.setEttevote(ettevote);
            model.addAttribute("osalus", osalusDto);
            return "ettevoteDetail.html";
        }

    }

    @RequestMapping(value = "/editeraisikuosalus", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("osalus") OsalusDto osalus,
                               BindingResult result) {
        Osalus osalusreferents = osalusService.get(osalus.getId());
        Isik isik = osalusreferents.getIsik();

        Eraisik eraisik = eraisikService.getByIsik(isik);
        eraisik.setEesnimi(osalus.getEraisik().getEesnimi());
        eraisik.setPerekonnanimi(osalus.getEraisik().getPerekonnanimi());
        eraisik.setIsikukood(osalus.getEraisik().getIsikukood());
        Eraisik eraisikSaved = eraisikService.saveEraisik(eraisik);

        isik.setNimi(eraisikSaved.getEesnimi() + " " + eraisikSaved.getPerekonnanimi());
        isik.setKood(eraisikSaved.getIsikukood());
        Isik isikSaved = isikService.saveCustomer(isik);
        Osalus osalusUus = new Osalus();
        osalusUus.setId(osalus.getId());
        osalusUus.setYritus(osalusreferents.getYritus());
        osalusUus.setIsik(isikSaved);
        osalusUus.setTulijatearv(1);
        osalusUus.setMakseviis(osalus.getMakseviis());
        osalusUus.setLisainfo(osalus.getLisainfo());
        osalusService.saveOsalus(osalusUus);

        return "redirect:/";
    }

    @RequestMapping(value = "/editettevotteosalus", method = RequestMethod.POST)
    public String editFirma(@ModelAttribute("osalus") OsalusDto osalus, BindingResult result) {

        Osalus osalusreferents = osalusService.get(osalus.getId());
        Isik isik = osalusreferents.getIsik();

        Ettevote ettevote = ettevoteService.getByIsik(isik);
        ettevote.setJuriidilinenimi(osalus.getEttevote().getJuriidilinenimi());
        ettevote.setRegistrikood(osalus.getEttevote().getRegistrikood());
        Ettevote ettevoteSaved = ettevoteService.saveEttevote(ettevote);

        isik.setNimi(ettevoteSaved.getJuriidilinenimi());
        isik.setKood(ettevoteSaved.getRegistrikood());
        Isik isikSaved = isikService.saveCustomer(isik);
        Osalus osalusUus = new Osalus();
        osalusUus.setId(osalus.getId());
        osalusUus.setYritus(osalusreferents.getYritus());
        osalusUus.setIsik(isikSaved);
        osalusUus.setTulijatearv(osalus.getTulijatearv());
        osalusUus.setMakseviis(osalus.getMakseviis());
        osalusUus.setLisainfo(osalus.getLisainfo());
        osalusService.saveOsalus(osalusUus);

        return "redirect:/";
    }
    @RequestMapping("/deleteOsalus/{id}")
    public String deleteIsik(@PathVariable(name = "id") Long id) {

        osalusService.delete(id);

        return "redirect:/";
    }



}
