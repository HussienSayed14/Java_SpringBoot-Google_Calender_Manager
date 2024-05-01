package com.ropulva.CalendarManagement.startEnd;

import com.ropulva.CalendarManagement.event.EventModel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "start_end", indexes = {
        @Index(name = "idx_event_id_start_end", columnList = "event_id")
})
public class StartEndModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    private EventModel event;
}
