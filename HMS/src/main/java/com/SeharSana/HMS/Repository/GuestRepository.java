package com.SeharSana.HMS.Repository;

import com.SeharSana.HMS.entity.Guest;
import com.SeharSana.HMS.model.GuestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {

    Guest findGuestById(Long id);
    Guest findGuestByEmail(String email);
    List<GuestModel> findGuestByIdAndEmail(Long id, String email);

}
