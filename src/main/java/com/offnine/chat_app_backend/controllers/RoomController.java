package com.offnine.chat_app_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.offnine.chat_app_backend.ChatAppBackendApplication;
import com.offnine.chat_app_backend.Repo.RoomRepo;
import com.offnine.chat_app_backend.entities.Message;
import com.offnine.chat_app_backend.entities.Room;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("*")
public class RoomController {


     @Autowired
     private RoomRepo roomRepo;


// create Room
@PostMapping
public ResponseEntity<?> createRoom (@RequestBody String roomid){
if(roomRepo.findByRoomId(roomid)!=null){
 return ResponseEntity.badRequest().body("Room Arleady Exist");
}
   Room room = new Room();
   room.setRoomId(roomid);
Room savedRoom = roomRepo.save(room);
     return ResponseEntity.status(HttpStatus.CREATED).body(room);
}


// get Room
@GetMapping("/{roomId}")
public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
   Room room = roomRepo.findByRoomId(roomId);
   if(room==null){
    return ResponseEntity.badRequest().body("Room not found");
   }
return ResponseEntity.ok(room);

}


// get  message of room

@GetMapping("/{roomId}/messages")
public ResponseEntity<List<Message>> getMessages(
    @PathVariable String roomId,
    @RequestParam(value = "page",defaultValue = "0",required = false) int page,
    @RequestParam(value = "size",defaultValue = "20",required = false) int size
){

 Room room = roomRepo.findByRoomId(roomId);
if(room ==null){
    return ResponseEntity.badRequest().build();
}
List<Message> messages = room.getMessages();
int start = Math.max(0,messages.size()-(page+1)*size);
int end = Math.min(messages.size(), start +size);
 List <Message> paginateMessages =  messages.subList(start, end);

 return ResponseEntity.ok(paginateMessages);
}






}
