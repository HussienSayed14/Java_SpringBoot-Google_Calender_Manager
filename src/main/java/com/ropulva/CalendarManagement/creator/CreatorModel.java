package com.ropulva.CalendarManagement.creator;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<EventModel> eventsList;

}
