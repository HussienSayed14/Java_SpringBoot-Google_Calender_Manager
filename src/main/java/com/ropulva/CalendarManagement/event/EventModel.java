package com.ropulva.CalendarManagement.event;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ropulva.CalendarManagement.creator.CreatorModel;
import com.ropulva.CalendarManagement.event.dto.EventDto;
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
@Table(name = "event",indexes = {@Index(name = "idx_event_status",columnList = "status")})
public class EventModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "status",length = 10)
    private String status;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "title", length = 60)
    private String title;
    @Column(name = "created")
    private Timestamp created;
    @Column(name = "updated")
    private Timestamp updates;
    @Column(name = "color_id",length = 15)
    private String colorId;
    @Column(name = "start_timestamp")
    private Timestamp startDate;
    @Column(name = "end_timestamp")
    private Timestamp endDate;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    @JsonIgnore
    private CreatorModel creator;

}

