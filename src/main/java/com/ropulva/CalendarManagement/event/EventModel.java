package com.ropulva.CalendarManagement.event;


import com.ropulva.CalendarManagement.attendee.AttendeeModel;
import com.ropulva.CalendarManagement.creator.CreatorModel;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "event")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "summary")
    private String summary;
    @Column(name = "created")
    private Timestamp created;
    @Column(name = "updated")
    private Timestamp updates;
    @Column(name = "color_id")
    private String colorId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;
    @OneToMany(mappedBy = "event")
    private List<AttendeeModel> attendeesList;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private CreatorModel creator;

}
