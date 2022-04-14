package ru.leadersofdigital.dobro.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "interest")
@NoArgsConstructor
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "interest", unique = true)
    private String interest;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_interest",
            joinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
    private List<Profile> profiles;

    public Interest(Long id) {
        this.id = id;
    }
}