package com.SeharSana.HMS.model;

import com.SeharSana.HMS.Utility.EnRoomType;
import com.SeharSana.HMS.entity.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RoomModel {
    private Long id;
    private Long roomNumber;
    private int bed;
    private long price;
    private EnRoomType enRoomType;
    private boolean isReserved;

    public Room disassemble() {
        Room room = new Room( );
        room.setId(this.getId( ));
        room.setRoomNumber(this.getRoomNumber( ));
        room.setBed(this.getBed( ));
        room.setRoomPrice(this.getPrice( ));
        room.isReserved( );
        room.setReservation(room.getReservation( ));
//        room.setEnRoomType(EnRoomType.LUXURY);
//        room.setEnRoomType(EnRoomType.BALCONY);
//        room.setEnRoomType(EnRoomType.STANDARD);
        room.setEnRoomType(enRoomType);
        return room;
    }

    public RoomModel assemble(Room room) {
        RoomModel roomModel = new RoomModel(room);
        roomModel.setId(room.getId( ));
        roomModel.setRoomNumber(room.getRoomNumber( ));
        roomModel.setBed(room.getBed( ));
        roomModel.setPrice(room.getRoomPrice( ));
        roomModel.setEnRoomType(room.getEnRoomType());
        return roomModel;
    }

    public RoomModel(Room room) {

        this.id = room.getId( );
        this.roomNumber = room.getRoomNumber( );
        this.bed = room.getBed( );
        this.price = room.getRoomPrice( );
        this.enRoomType = room.getEnRoomType();

    }

    public RoomModel() {

    }
}


