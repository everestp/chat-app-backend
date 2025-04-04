package com.offnine.chat_app_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Changed to UUID for String ID
    @Column(columnDefinition = "VARCHAR(36)")  // Explicit column definition for UUID
    private String id;
    
    @Column(unique = true)
    private String roomId;
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();
}