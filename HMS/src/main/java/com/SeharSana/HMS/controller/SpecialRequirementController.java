package com.SeharSana.HMS.controller;

import com.SeharSana.HMS.model.SpecialRequirementModel;
import com.SeharSana.HMS.service.SpecialRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/specialRequirement")
public class SpecialRequirementController {
    @Autowired
    SpecialRequirementService specialRequirementService;
    @PostMapping("/save")
    public SpecialRequirementModel addSpecialRequirements(@RequestBody SpecialRequirementModel specialRequirementsModel){
        return specialRequirementService.addSpecialRequirements(specialRequirementsModel);
    }
    @GetMapping("/{id}")
    public List<SpecialRequirementModel> findByGuestId(@PathVariable long guestId){
        return specialRequirementService.findByGuestId(guestId);
    }
}
