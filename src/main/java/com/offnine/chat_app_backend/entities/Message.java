package com.offnine.chat_app_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Changed to UUID for String ID
    @Column(columnDefinition = "VARCHAR(36)")  // Explicit column definition for UUID
    private String id;
    
    private String sender;
    private String content;
    private LocalDateTime timeStamp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")  // Explicit foreign key
    private Room room;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }
}