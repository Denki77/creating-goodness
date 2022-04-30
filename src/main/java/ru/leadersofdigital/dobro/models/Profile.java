package ru.leadersofdigital.dobro.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToMany
    @JoinTable(
            name = "profile_interest",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"))
    private List<Interest> interests;

    @ManyToMany
    @JoinTable(
            name = "profile_tags",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;
}