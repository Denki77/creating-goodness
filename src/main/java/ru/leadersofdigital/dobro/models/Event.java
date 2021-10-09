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

    //название
    @Column(name = "name")
    private String name;

    //описание
    @Column(name = "comm")
    private String comm;

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
    @JoinColumn(name = "user_id")
    private User user;

    //пользователи подписчики события
    @OneToMany
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private List<User> subscribers;
}
