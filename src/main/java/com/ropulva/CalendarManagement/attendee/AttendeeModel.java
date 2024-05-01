package com.ropulva.CalendarManagement.attendee;

import com.ropulva.CalendarManagement.event.EventModel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "attendee", indexes = {
        @Index(name = "idx_event_id_attendee", columnList = "event_id"),
        @Index(name = "idx_email_attendee", columnList = "email")
})
public class AttendeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private Timestamp created;
    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    private EventModel event;
}
