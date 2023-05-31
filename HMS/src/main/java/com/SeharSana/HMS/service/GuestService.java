package com.SeharSana.HMS.service;

import com.SeharSana.HMS.Repository.GuestRepository;
import com.SeharSana.HMS.entity.Guest;
import com.SeharSana.HMS.model.GuestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuestService {
        @Autowired
        public GuestRepository guestRepository;
        public GuestModel saveGuest(GuestModel guestModel)
        {
            return guestModel.assemble(guestRepository.save(guestModel.disassemble()));
        }

        public List<Guest> getGuest(Long id, String email)
        {
            if (id != null && email != null) {
                guestRepository.findGuestByIdAndEmail(id, email);
            } else if (id != null) {
                guestRepository.findGuestById(id);
            } else if (email != null) {
                guestRepository.findGuestByEmail(email);
            }
            return guestRepository.findAll();
        }

        public void deleteGuest(Long id)
        {
            GuestModel guest = guestRepository.findGuestById(id)
                            .orElseThrow("Guest Not Found");
            guestRepository.delete(guest.disassemble( ));

        }


}


