package com.ropulva.CalendarManagement.creator;


import com.ropulva.CalendarManagement.event.EventModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "creator", indexes = {
        @Index(name = "idx_event_id_creator", columnList = "event_id")
})
public class CreatorModel {
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    private EventModel event;


}
