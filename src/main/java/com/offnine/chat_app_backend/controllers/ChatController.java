package com.offnine.chat_app_backend.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.offnine.chat_app_backend.ChatAppBackendApplication;
import com.offnine.chat_app_backend.Repo.RoomRepo;
import com.offnine.chat_app_backend.Requests.MessageRequest;
import com.offnine.chat_app_backend.entities.Message;
import com.offnine.chat_app_backend.entities.Room;

@Controller
public class ChatController {


    

@Autowired
 private RoomRepo roomRepo;



 // for sending and receivintg messsage

 @MessageMapping("/sendMessage/{roomId}")
 @SendTo("/topic/room/{roomId}")  // subscibe the message by ckient
 public Message sendMessage(
 @RequestBody MessageRequest req,
 @DestinationVariable String  roomId

 ){
    
 Room room =roomRepo.findByRoomId(roomId);
 Message message = new Message();
 message.setContent(req.getContent());
 message.setSender(req.getSender());
message.setTimeStamp(LocalDateTime.now());
if(room != null){
    room.getMessages().add(message);
    roomRepo.save(room);
}
else{
   throw new RuntimeException("Room not found");
}

return message;



 }



}
