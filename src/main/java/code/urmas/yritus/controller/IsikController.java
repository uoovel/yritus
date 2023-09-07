package code.urmas.yritus.controller;

import code.urmas.yritus.model.*;
import code.urmas.yritus.service.*;
import code.urmas.yritus.dto.OsalusDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpRequest;
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
        osalusDto.setYritusid((long) id);
        osalusDto.setYritusenimetus(yritus.getNimetus());
        osalusDto.setYrituseaeg(yritus.getAeg());
        osalusDto.setYritusekoht(yritus.getKoht());
        Isik isik = new Isik();
        //osalusDto.setIsik(isik);
        //Eraisik
        Tyyp tyyp = tyypService.getById(Long.valueOf(1));
        List<Isik> listIsikEra = isikService.listByTyyp(tyyp);
        model.addAttribute("isikListEra" ,listIsikEra);
        tyyp = tyypService.getById(Long.valueOf(2));
        List<Isik> listIsikEtt = isikService.listByTyyp(tyyp);
        model.addAttribute("isikListEtt" ,listIsikEtt);
        model.addAttribute("osalus", osalusDto);
        List<Tyyp> listTyyp = tyypService.listAll();
        model.addAttribute("listTyyp" ,listTyyp);
        List<Makseviis> listMakseviis = makseviisService.listAll();
        model.addAttribute("makseviisList" ,listMakseviis);
        return "vormOsaleja.html";
    }

    @RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
    public String saveCustomer(
            @Valid @ModelAttribute("osalus") OsalusDto osalus,
            BindingResult result, Model model,
            HttpServletRequest request) {

        //String contType = request.getContentType();
        Long eraisikId = Long.valueOf(0);
        Long ettevoteId = Long.valueOf(0);
        Isik isik = new Isik();
        Isik isikSaved = null;
        Boolean olemasolev = false;
        Boolean oneraisik = false;
        Boolean onettevote = false;
        Long tyypid = osalus.getIsikutyypid();
        Tyyp tyyp = tyypService.getById(tyypid);
        if(osalus.getIsikutyypid() == 1){
            oneraisik = true;
            try{
                                //eraisikId = osalus.getEraisik().getIsik().getId();
                eraisikId = osalus.getEraisikid();
                //System.out.println("IsikController100: " + eraisikId);
                if(eraisikId != 0){
                    isik = isikService.get(eraisikId);
                    isikSaved = isik;
                    olemasolev = true;
                }
            }catch (Exception e){

            }
        }

        if(osalus.getIsikutyypid() == 2){
            onettevote = true;
            try{
                ettevoteId = osalus.getEttevoteid();
                //System.out.println("IsikController200: " + ettevoteId);
                if(ettevoteId != 0){
                    isik = isikService.get(ettevoteId);
                    isikSaved = isik;
                    olemasolev = true;
                }
            }catch (Exception e){

            }
        }

        if(result.hasErrors() && !olemasolev && oneraisik) {
            model.addAttribute("veaga", "jah");
            model.addAttribute("osalus", osalus);
            List<Tyyp> listTyyp = tyypService.listAll();
            model.addAttribute("listTyyp" ,listTyyp);
            List<Makseviis> listMakseviis = makseviisService.listAll();
            model.addAttribute("makseviisList" ,listMakseviis);
            return "vormOsaleja.html";
        }


        //Salvesta osalus
        osalusService.salvestaOsalus(oneraisik, onettevote, olemasolev, isik, osalus, tyyp, isikSaved);

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
        osalusDto.setMakseviisid(osalus.getMakseviis().getId());
        osalusDto.setMakseviisinimetus(osalus.getMakseviis().getNimetus());
        osalusDto.setLisainfo(osalus.getLisainfo());
        List<Makseviis> listMakseviis = makseviisService.listAll();
        model.addAttribute("makseviisList" ,listMakseviis);
        if(tyypId == 1){
            Eraisik eraisik = eraisikService.getByIsik(isik);
            osalusDto.setEraisikid(eraisik.getId());
            osalusDto.setEesnimi(eraisik.getEesnimi());
            osalusDto.setPerekonnanimi(eraisik.getPerekonnanimi());
            osalusDto.setIsikukood(eraisik.getIsikukood());
            model.addAttribute("osalus", osalusDto);
            return "eraisikDetail.html";
        }else{
            Ettevote ettevote = ettevoteService.getByIsik(isik);
            osalusDto.setEttevoteid(ettevote.getId());
            osalusDto.setJuriidilinenimi(ettevote.getJuriidilinenimi());
            osalusDto.setRegistrikood(ettevote.getRegistrikood());
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
        eraisik.setEesnimi(osalus.getEesnimi());
        eraisik.setPerekonnanimi(osalus.getPerekonnanimi());
        eraisik.setIsikukood(osalus.getIsikukood());
        Eraisik eraisikSaved = eraisikService.saveEraisik(eraisik);
        isik.setNimi(eraisikSaved.getEesnimi() + " " + eraisikSaved.getPerekonnanimi());
        isik.setKood(eraisikSaved.getIsikukood());
        Isik isikSaved = isikService.saveCustomer(isik);
        Osalus osalusUus = new Osalus();
        osalusUus.setId(osalus.getId());
        osalusUus.setYritus(osalusreferents.getYritus());
        osalusUus.setIsik(isikSaved);
        osalusUus.setTulijatearv(1);
        Makseviis makseviis = makseviisService.get(osalus.getMakseviisid());
        osalusUus.setMakseviis(makseviis);
        osalusUus.setLisainfo(osalus.getLisainfo());
        osalusService.saveOsalus(osalusUus);
        return "redirect:/";
    }

    @RequestMapping(value = "/editettevotteosalus", method = RequestMethod.POST)
    public String editFirma(@ModelAttribute("osalus") OsalusDto osalus, BindingResult result) {

        Osalus osalusreferents = osalusService.get(osalus.getId());
        Isik isik = osalusreferents.getIsik();
        Ettevote ettevote = ettevoteService.getByIsik(isik);
        ettevote.setJuriidilinenimi(osalus.getJuriidilinenimi());
        ettevote.setRegistrikood(osalus.getRegistrikood());
        Ettevote ettevoteSaved = ettevoteService.saveEttevote(ettevote);
        isik.setNimi(ettevoteSaved.getJuriidilinenimi());
        isik.setKood(ettevoteSaved.getRegistrikood());
        Isik isikSaved = isikService.saveCustomer(isik);
        Osalus osalusUus = new Osalus();
        osalusUus.setId(osalus.getId());
        osalusUus.setYritus(osalusreferents.getYritus());
        osalusUus.setIsik(isikSaved);
        osalusUus.setTulijatearv(osalus.getTulijatearv());
        Makseviis makseviis = makseviisService.get(osalus.getMakseviisid());
        osalusUus.setMakseviis(makseviis);
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
