package com.SeharSana.HMS.model;

import com.SeharSana.HMS.entity.SpecialRequirement;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SpecialRequirementModel {
    private Long id;
    private String carParking;
    private String roomService;
    private GuestModel guestModel;

    public SpecialRequirement disassemble(){
        SpecialRequirement specialRequirements=new SpecialRequirement();
        specialRequirements.setId(id);
        specialRequirements.setCarParking(carParking);
        specialRequirements.setRoomService(roomService);
        specialRequirements.setGuest(guestModel.disassemble());
        return specialRequirements;
    }
    public SpecialRequirementModel assemble(SpecialRequirement specialRequirements){
        SpecialRequirementModel specialRequirementsModel=new SpecialRequirementModel();
        specialRequirementsModel.setId(specialRequirements.getId());
        specialRequirementsModel.setCarParking(specialRequirements.getCarParking());
        specialRequirementsModel.setRoomService(specialRequirements.getRoomService());
        specialRequirementsModel.setGuestModel(new GuestModel().assemble(specialRequirements.getGuest()));
        return specialRequirementsModel;
    }
//    public SpecialRequirementsModel(Long id, String carParking, String roomService){
//        this.id=id;
//        this.carParking=carParking;
//        this.roomService=roomService;
//
//    }
    public SpecialRequirementModel(){

    }
}
