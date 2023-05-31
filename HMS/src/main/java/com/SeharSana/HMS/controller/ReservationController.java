package com.SeharSana.HMS.controller;

import com.SeharSana.HMS.Repository.ReservationRepository;
import com.SeharSana.HMS.entity.Reservation;
import com.SeharSana.HMS.model.ReservationModel;
import com.SeharSana.HMS.model.RoomModel;
import com.SeharSana.HMS.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@Lazy
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @PostMapping("/save")
    public ResponseEntity<ReservationModel> createReservation(@RequestBody ReservationModel reservationModel) {
        ReservationModel createdReservation = reservationService.createReservation(reservationModel);
        if (createdReservation == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        }
    }


    @GetMapping("/list")
    public List<Reservation> getAllReservations()
    {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id)
    {
        Reservation reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
    @GetMapping("/checkin-date-available/{checkInDate}")
    public boolean isCheckInDateAvailable(@RequestBody RoomModel roomModel
            , @PathVariable("checkInDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate checkInDate){
        return reservationService.isCheckInDateAvailable(roomModel,checkInDate);
    }


    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id
            , @RequestBody Reservation reservationUpdates)
    {
        return reservationService.updateReservation(id, reservationUpdates);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);

    }
}
