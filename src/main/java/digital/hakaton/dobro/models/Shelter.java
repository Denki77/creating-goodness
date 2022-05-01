package digital.hakaton.dobro.models;


import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "shelters")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "names")
    private String name;

    @Column(name = "number")
    private Long number;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shelter_id")
//    private List<Profile> profiles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Shelter() {
    }

    public Shelter(Long id) {
        this.id = id;
    }
}