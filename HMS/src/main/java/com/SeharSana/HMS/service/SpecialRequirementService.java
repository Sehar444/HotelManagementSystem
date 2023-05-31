package com.SeharSana.HMS.service;

import com.SeharSana.HMS.Repository.SpecialRequirementRepository;
import com.SeharSana.HMS.entity.SpecialRequirement;
import com.SeharSana.HMS.model.SpecialRequirementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialRequirementService {
    @Autowired
    private SpecialRequirementRepository specialRequirementRepository;
    @Autowired
    private SpecialRequirementModel specialRequirementModel;

    public SpecialRequirementModel addSpecialRequirements(SpecialRequirementModel specialRequirementsModel) {
        return specialRequirementsModel.assemble(specialRequirementRepository
                .save(specialRequirementsModel.disassemble()));
    }

    public List<SpecialRequirementModel> findByGuestId(long guestId) {
        List<SpecialRequirement> specialRequirementsList = specialRequirementRepository.findByGuestId(guestId);
        return specialRequirementsList.stream()
                .map(specialRequirementModel::assemble).collect(Collectors.toList());
    }
}
