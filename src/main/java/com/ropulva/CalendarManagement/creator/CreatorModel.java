package com.ropulva.CalendarManagement.creator;

import com.ropulva.CalendarManagement.event.EventModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "creator")
public class CreatorModel {
    @Id
    @Column(name = "email",length = 50)
    private String email;
    @Column(name = "name",length = 30)
    private String name;
    @OneToMany(mappedBy = "creator")
    private List<EventModel> eventsList;

}
