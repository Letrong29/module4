package com.codegym.controller;

import com.codegym.dto.facility.FacilityDto;
import com.codegym.model.facility.Facility;
import com.codegym.model.facility.FacilityType;
import com.codegym.model.facility.RentType;
import com.codegym.service.IFacilityService;
import com.codegym.service.IFacilityTypeService;
import com.codegym.service.IRentTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private IFacilityService iFacilityService;

    @Autowired
    private IRentTypeService iRentTypeService;

    @Autowired
    private IFacilityTypeService iFacilityTypeService;

    @GetMapping(value = {"/home",""})
    public String goFacilityList(@PageableDefault(size = 3)Pageable pageable,
                                 @RequestParam Optional<String> keySearch,
                                 Model model){

        String keyVal = keySearch.orElse("");

        model.addAttribute("facilitys",
                this.iFacilityService.findAllByNameContaining(keyVal, pageable));

        model.addAttribute("keySearch", keyVal);

        return "facility-list";
    }

    @GetMapping("/create")
    public String goCreateForm(Model model){

        model.addAttribute("facilityDto", new FacilityDto());

        model.addAttribute("facilityTypeList",
                this.iFacilityTypeService.findAll());

        model.addAttribute("rentTypeList",
                this.iRentTypeService.findAll());

        return "facility-create";
    }

    @PostMapping("/save")
    public String saveFacility(@ModelAttribute @Valid FacilityDto facilityDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model){

        new FacilityDto().validate(facilityDto, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("facilityTypeList",
                    this.iFacilityTypeService.findAll());

            model.addAttribute("rentTypeList",
                    this.iRentTypeService.findAll());

            return "facility-create";
        }

        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDto, facility);

        FacilityType facilityType = new FacilityType();
        facilityType.setId(facilityDto.getFacilityType().getId());
        facilityDto.setFacilityType(facilityType);

        RentType rentType = new RentType();
        rentType.setId(facilityDto.getRentType().getId());
        facilityDto.setRentType(rentType);

        this.iFacilityService.save(facility);

        redirectAttributes.addFlashAttribute("message",
                "successfully added new");

        return "redirect:/facility/home";
    }

    @GetMapping("/edit")
    public String goEditForm(@RequestParam int id, Model model){

        model.addAttribute("facility",
                this.iFacilityService.findById(id));

        model.addAttribute("facilityTypeList",
                this.iFacilityTypeService.findAll());

        model.addAttribute("rentTypeList",
                this.iRentTypeService.findAll());

        return "facility-edit";
    }

    @PostMapping("/update")
    public String updateFacility(@ModelAttribute Facility facility, RedirectAttributes redirectAttributes){

        this.iFacilityService.save(facility);

        redirectAttributes.addFlashAttribute("message",
                "successfully update");

        return "redirect:/facility/home";
    }

    @PostMapping("/delete")
    public String deleteFacility(@RequestParam int facilityId){

        this.iFacilityService.deleteById(facilityId);

        return "redirect:/facility";
    }
}
