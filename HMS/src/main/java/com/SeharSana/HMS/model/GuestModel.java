package com.SeharSana.HMS.model;
import com.SeharSana.HMS.entity.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GuestModel {
    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;
    private String cnic;
    public Guest disassemble()
    {
        Guest guest = new Guest();
        guest.setId(id);
        guest.setName(name);
        guest.setEmail(email);
        guest.setCnic(cnic);
        guest.setPhoneNumber(phoneNumber);
        return guest;
    }

    public GuestModel assemble(Guest guest) {
        GuestModel guestModel = new GuestModel( );
        guestModel.setId(guest.getId( ));
        guestModel.setName(guest.getName( ));
        guestModel.setEmail(guest.getEmail( ));
        guestModel.setCnic(guest.getCnic());
        guest.setPhoneNumber(guest.getPhoneNumber( ));
        return guestModel;
    }
    public GuestModel(Guest guest)
    {
        this.id=guest.getId();
        this.name=guest.getName();
        this.email=guest.getEmail();
        this.cnic=guest.getCnic();
        this.phoneNumber=guest.getPhoneNumber();

    }
    public  GuestModel(){

    }


}




