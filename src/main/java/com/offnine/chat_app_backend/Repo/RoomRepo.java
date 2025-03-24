package com.offnine.chat_app_backend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.offnine.chat_app_backend.entities.Room;

import java.util.Optional;

@Repository
public interface RoomRepo extends MongoRepository<Room, String> {

    // Get room using room ID (returns Optional to avoid NullPointerException)
    Optional<Room> findByRoomId(String roomId);
}
