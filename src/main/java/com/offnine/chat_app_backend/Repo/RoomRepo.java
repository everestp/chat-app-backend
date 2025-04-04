package com.offnine.chat_app_backend.Repo;


import org.springframework.data.jpa.repository.JpaRepository;


import com.offnine.chat_app_backend.entities.Room;




public interface RoomRepo extends  JpaRepository<Room,String>{

    // get  room using room id
  Room  findByRoomId(String roomId);
}
