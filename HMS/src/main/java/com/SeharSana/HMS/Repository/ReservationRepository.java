package com.SeharSana.HMS.Repository;

import com.SeharSana.HMS.entity.Reservation;
import com.SeharSana.HMS.entity.Room;
import com.SeharSana.HMS.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<ReservationModel> findByRoom(Room room);
}
