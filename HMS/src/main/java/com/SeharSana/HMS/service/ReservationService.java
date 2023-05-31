package com.SeharSana.HMS.service;

import com.SeharSana.HMS.Repository.GuestRepository;
import com.SeharSana.HMS.Repository.ReservationRepository;
import com.SeharSana.HMS.Repository.RoomRepository;
import com.SeharSana.HMS.entity.Guest;
import com.SeharSana.HMS.entity.Reservation;
import com.SeharSana.HMS.entity.Room;
import com.SeharSana.HMS.model.ReservationModel;
import com.SeharSana.HMS.model.RoomModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;
//    public ReservationModel createReservation(ReservationModel reservationModel)
//
//    {
//        return reservationModel.assemble(reservationRepository.save(reservationModel.disassemble()));
//    }
//public ReservationModel createReservation( ReservationModel reservationModel){
//     Get the flight schedule for the reservation
//    Room room = roomRepository.findById(reservationModel.getRoomModel().getId())
//            .orElseThrow(() -> new RuntimeException("Room not found"));
//
//    Check the reservation category and update the corresponding capacity in the airline flight
//    Guest guest = (Guest) room.getReservation();
////    if ("LUXURY".equalsIgnoreCase(reservationModel.getReservationCategory())) {
////        int luxuryCapacity = Integer.parseInt(guest.getLuxuryCapacity());
////        if (luxuryCapacity > 0) {
////            guest.setEconomyCapacity(String.valueOf(economyCapacity - 1));
//            guestRepository.save(guest);
////            flightSchedule.setFlightStatus("CLOSE");
//            roomRepository.save(room);
////    } else if ("business".equalsIgnoreCase(reservationModel.getReservationCategory())) {
////        int businessCapacity = Integer.parseInt(airLineFlight.getBusinessCapacity());
////        if (businessCapacity > 0) {
////            airLineFlight.setBusinessCapacity(String.valueOf(businessCapacity - 1));
////            airLineFlightRepository.save(airLineFlight);
////        } else {
////            // Set the flight status to "CLOSE" if business capacity is 0
////            flightSchedule.setFlightStatus("CLOSE");
////            flightScheduleRepository.save(flightSchedule);
////            throw new RuntimeException("Business capacity is full for the selected flight");
////        }
////    } else {
////        throw new RuntimeException("Invalid reservation category");
////    }
//
//    Save the reservation
//    Reservation reservation = reservationModel.disassemble();
//    reservation.setRoom(room);
//    Guest guest1=guestRepository.findById(reservationModel.getGuestModel( ).getId( ))
//            .orElseThrow(() -> new RuntimeException("Guest not found"));
//    reservation.setGuest(guest1);
//    return reservationModel.assemble(reservationRepository.save(reservationModel.disassemble()));
//}

    public ReservationModel createReservation(ReservationModel reservationModel) {
        Reservation reservation = reservationModel.disassemble();

        // Get the room and guest entities from their respective repositories
        Optional<Room> room = roomRepository.findById(reservation.getRoom().getId());
        Optional<Guest> guest = guestRepository.findById(reservation.getGuest().getId());

        if (!room.isPresent() || !guest.isPresent()) {
            // If either the room or guest entity is not found, throw an exception
            throw new IllegalArgumentException("Room or guest not found.");
        }

        // Set the room and guest entities in the reservation entity
        reservation.setRoom(room.get());
        reservation.setGuest(guest.get());

        // Save the reservation entity to the database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Assemble the saved reservation entity into a reservation model and return it
        return new ReservationModel(savedReservation);
    }


public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id)
    {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent( )) {
            return reservation.get( );
        } else
        {
            throw new EntityNotFoundException("Reservation with id " + id + " not found.");
        }
    }


    public Reservation updateReservation(Long id, Reservation reservationUpdates)
    {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent( )) {
            Reservation existingReservation = reservation.get( );
            existingReservation.setCheckIn(reservationUpdates.isCheckIn( ));
            existingReservation.setCheckOut(reservationUpdates.isCheckOut( ));
            existingReservation.setStatus(reservationUpdates.getStatus( ));
            existingReservation.setCheckInDate(reservationUpdates.getCheckInDate());
            existingReservation.setCheckOut(reservationUpdates.getCheckOutDate());
            existingReservation.setGuest(reservationUpdates.getGuest( ));
            existingReservation.setRoom(reservationUpdates.getRoom( ));
            return reservationRepository.save(existingReservation);
        } else
        {
            throw new EntityNotFoundException("Reservation with id " + id + " not found.");
        }
    }
    public boolean isCheckInDateAvailable(RoomModel roomModel, LocalDate checkInDate) {
        List<ReservationModel> reservations = reservationRepository.findByRoom(roomModel.disassemble());
        for (ReservationModel reservationModel : reservations) {
            LocalDate reservedCheckInDate = LocalDate.parse(reservationModel.getCheckInDate());
            LocalDate reservedCheckOutDate = LocalDate.parse(reservationModel.getCheckOutDate());
            if (checkInDate.isAfter(reservedCheckInDate) && checkInDate.isBefore(reservedCheckOutDate)) {
                return false;
            }
            if (checkInDate.equals(reservedCheckInDate) || checkInDate.equals(reservedCheckOutDate)) {
                return false;
            }
        }
        return true;
    }


    public void deleteReservation(Long id)
    {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent( ))
        {
            reservationRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Reservation with id " + id + " not found.");
        }
    }
}