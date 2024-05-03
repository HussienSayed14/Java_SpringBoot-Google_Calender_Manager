package com.ropulva.CalendarManagement.event;


import com.ropulva.CalendarManagement.attendee.AttendeeModel;
import com.ropulva.CalendarManagement.creator.CreatorModel;
import com.ropulva.CalendarManagement.startEnd.StartEndModel;
import jakarta.persistence.*;
import lombok.*;


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

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private StartEndModel startEndDate;

    @OneToMany(mappedBy = "event")
    private List<AttendeeModel> attendeesList;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private CreatorModel creator;

}
