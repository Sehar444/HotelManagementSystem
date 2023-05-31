package com.SeharSana.HMS.entity;

import com.SeharSana.HMS.model.GuestModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name="guest")
@Data
@Component
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "guest_name")
    private String name;
    @Column(name = "guest_Cnic")
    private String cnic;
    @Column(name = "guest_email")
    private String email;
    @Column(name = "guest_phone_number")
    private Long phoneNumber;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservation;
    @OneToMany(mappedBy = "guest")
    private List<SpecialRequirement> specialRequirements;

    public GuestModel orElseThrow(String guestNotFound) {
        return new GuestModel();
    }
}