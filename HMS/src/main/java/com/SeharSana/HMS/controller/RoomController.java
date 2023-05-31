package com.SeharSana.HMS.controller;

import com.SeharSana.HMS.Utility.EnRoomType;
import com.SeharSana.HMS.entity.Room;
import com.SeharSana.HMS.model.RoomModel;
import com.SeharSana.HMS.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping( "/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/save")
    public ResponseEntity<RoomModel> saveRoom(@RequestBody RoomModel roomModel)
    {
        RoomModel savedRoom = roomService.createRoom(roomModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }
    @PutMapping("/byId/{id}")
    public RoomModel updateRoom(@PathVariable Long id, @RequestBody RoomModel roomModel)
    {
        roomModel.setId(id);
        return roomService.updateRoom(id,roomModel);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id)
    {
        return roomService.getRoomById(id);
    }



    @GetMapping("/list")
    public List<RoomModel> getaAllRoomsList()
    {
        List<RoomModel> roomModelList=roomService.getAllRooms();
        return roomModelList;
    }
    @GetMapping("/reserved/{isReserved}")
    public List<RoomModel> findByIsReserved(@PathVariable boolean isReserved) {
        List<RoomModel> roomModels = roomService.findByIsReserved(isReserved);
        return roomModels;
    }
    @GetMapping("/type/{enRoomType}")
    public List<RoomModel> getRoomsByEnRoomType(@PathVariable String enRoomType) {
        List<RoomModel> roomModels=roomService.getRoomsByEnRoomType(enRoomType);
        return roomModels;
    }



    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id)
    {
        roomService.deleteRoom(id);
    }
}


