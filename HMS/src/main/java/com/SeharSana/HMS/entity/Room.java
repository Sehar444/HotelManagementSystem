package com.SeharSana.HMS.entity;

import com.SeharSana.HMS.Utility.EnRoomType;
import com.SeharSana.HMS.exception.RoomNotFoundException;
import com.SeharSana.HMS.model.RoomModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name="room")
@Data
@Component
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "room_no", unique = true)
    private Long  roomNumber;
    @Column(name = "bed")
    private int bed;
    @Column(name="room_price")
    private long roomPrice;
    @Column(name = "is_reserved")
    private boolean isReserved;
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private EnRoomType enRoomType;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservation;

    public Room()
    {
    }
}
