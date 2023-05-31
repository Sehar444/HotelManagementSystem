package com.SeharSana.HMS.model;
import com.SeharSana.HMS.Utility.ReservationStatus;
import com.SeharSana.HMS.entity.Guest;
import com.SeharSana.HMS.entity.Reservation;
import com.SeharSana.HMS.entity.Room;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data

public class ReservationModel {
    private long id;
    private boolean checkIn;
    private boolean checkOut;
    private String checkInDate;
    private String checkOutDate;
    private boolean isCancelled;
    private ReservationStatus status;
    private String reservationCategory;

    private long guestId;
    private RoomModel roomModel;
    private GuestModel guestModel;

    public Reservation disassemble() {
        Reservation reservation = new Reservation( );
        reservation.setId(id);
//        reservation.setStatus(ReservationStatus.CHECKED_IN);
//        reservation.setStatus(ReservationStatus.CHECKED_OUT);
//        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.setCheckIn(isCheckIn());
        reservation.setCheckOut(isCheckOut());
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckInDate(checkOutDate);
        reservation.setReservationCategory(reservationCategory);
        reservation.setStatus(status);
        reservation.isCheckIn();
        reservation.isCheckOut();
        reservation.setRoom(roomModel.disassemble( ));
        reservation.setGuest(guestModel.disassemble( ));
        return reservation;
    }

    public ReservationModel assemble(Reservation reservation) {
        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setId(reservation.getId());
        reservationModel.setCheckIn(reservation.isCheckIn());
        reservationModel.setCheckOut(reservation.isCheckOut( ));
        reservationModel.setCheckOut(reservationModel.isCheckOut( ));
        reservationModel.setCheckInDate(reservation.getCheckInDate());
        reservationModel.setCheckOutDate(reservation.getCheckOutDate());
        reservationModel.setReservationCategory(reservation.getReservationCategory());
        reservationModel.setStatus(reservation.getStatus());
        reservationModel.setRoomModel(new RoomModel(reservation.getRoom( )).assemble(reservation.getRoom( )));
      reservationModel.setGuestModel(new GuestModel( reservation.getGuest()).assemble(reservation.getGuest()));
        return reservationModel;
    }


    public ReservationModel(Reservation reservation) {
        this.id = reservation.getId( );
        this.checkIn = reservation.isCheckIn( );
        this.checkOut = (boolean) reservation.isCheckOut( );
        this.roomModel = new RoomModel(reservation.getRoom() );
        this.guestModel = new GuestModel(reservation.getGuest( ));
        this.reservationCategory=reservation.getReservationCategory();
        this.checkInDate=reservation.getCheckInDate();
        this.checkOutDate=reservation.getCheckOutDate();

    }
    public ReservationModel(){

    }
}




