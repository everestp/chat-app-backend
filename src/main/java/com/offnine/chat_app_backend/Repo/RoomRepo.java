package com.offnine.chat_app_backend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.offnine.chat_app_backend.entities.Room;
import java.util.List;
import java.util.Optional;


public interface RoomRepo extends MongoRepository<Room,String>{

    // get  room using room id
  Room  findByRoomId(String roomId);
}
