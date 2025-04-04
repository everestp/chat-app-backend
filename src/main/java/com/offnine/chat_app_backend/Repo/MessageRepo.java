package com.offnine.chat_app_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offnine.chat_app_backend.entities.Message;

public interface MessageRepo extends  JpaRepository<Message,String>{

  
}
