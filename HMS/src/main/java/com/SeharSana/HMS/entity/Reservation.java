package com.SeharSana.HMS.entity;

import com.SeharSana.HMS.Utility.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "check_In_Date")
    private String checkInDate;
    @Column(name = "check_Out_Date")
    private String checkOutDate;
    @Column(name="reservation_category")
    private String reservationCategory;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Column(name = "status")
    private ReservationStatus status;

    public boolean isCheckIn()
    {

        return true;
    }

    public boolean isCheckOut()
    {
        return true;
    }

    public void setCheckIn(boolean checkIn)
    {
    }

    public void setCheckOut(Object checkOut) {
    }



}