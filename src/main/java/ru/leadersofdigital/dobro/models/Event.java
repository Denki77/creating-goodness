package ru.leadersofdigital.dobro.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.leadersofdigital.dobro.enums.EventStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "names")
    private String name;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "description")
    private String description;

    //статус
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EventStatus status;

    //начало события
    @Column(name = "start_date")
    private LocalDateTime startDate;

    //количество дней проведения
    @Column(name = "count_days")
    private Integer countDays;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //пользователь создатель события
    @OneToOne
    @JoinColumn(name = "maintainer_profile_id")
    private Profile profile;

    //пользователи подписчики события
    @ManyToMany
    @JoinTable(
            name = "profile_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> subscribers;
}