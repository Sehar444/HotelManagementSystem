package com.SeharSana.HMS.Repository;

import com.SeharSana.HMS.Utility.EnRoomType;
import com.SeharSana.HMS.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findRoomByRoomNumber(Long roomNumber);
    List<Room> findByRoomNumberAndReservationIsNull(Long roomNumber);
    List<Room> findByIsReserved(boolean isReserved);
    List<Room> findByEnRoomType(EnRoomType enRoomType);
}
