package com.SeharSana.HMS.service;

import com.SeharSana.HMS.Repository.RoomRepository;
import com.SeharSana.HMS.Utility.EnRoomType;
import com.SeharSana.HMS.entity.Room;
import com.SeharSana.HMS.exception.ResourceNotFoundException;
import com.SeharSana.HMS.exception.RoomNotFoundException;
import com.SeharSana.HMS.model.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomModel createRoom(RoomModel roomModel) {
        return roomModel.assemble(roomRepository.save(roomModel.disassemble()));
    }

    public RoomModel updateRoom(Long roomId, RoomModel roomModel) {
        Room room = roomModel.disassemble();
        Room existingRoom = null;
        try
        {
            existingRoom = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room", "id", +roomId));
        }
        catch (ResourceNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        existingRoom.setBed(room.getBed());
        existingRoom.setEnRoomType(room.getEnRoomType());
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomPrice(room.getRoomPrice());
        Room updatedRoom = roomRepository.save(existingRoom);
        return new RoomModel(updatedRoom);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow( ()-> new RoomNotFoundException("Room Not Found") );
    }
//    public RoomModel getRoomByNumber(Long roomNumber) {
//        Room room = roomRepository.findRoomByRoomNumber(roomNumber);
//        return new RoomModel().assemble(room);
//    }


    public List<RoomModel> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomModel> roomModels = new ArrayList<>();
        for (Room room : rooms) {
            roomModels.add(new RoomModel(room));
        }
        return roomModels;
    }


//    public List<Room> findAvailableRoomsByType(EnRoomType roomType)
//    {
//        return roomRepository.findByRoomTypeAndReservationIsNull(roomType);
//    }
    public List<RoomModel> findByIsReserved(boolean isReserved)
    {
        List<Room> rooms=roomRepository.findByIsReserved(isReserved);
        List<RoomModel> roomModels=new ArrayList<>();
        roomModels.contains(rooms);
        return roomModels;
    }
    public List<RoomModel> getRoomsByEnRoomType(String enRoomType) {
        EnRoomType roomType = EnRoomType.fromString(enRoomType);
        if (roomType == null) {
            return new ArrayList<>();
        }
        List<Room> rooms = roomRepository.findByEnRoomType(roomType);
        return rooms
                .stream()
                .map(RoomModel::new)
                .collect(Collectors.toList());
    }
public void deleteRoom(Long id)
    {
        roomRepository.deleteById(id);

    }
}
