package ru.leadersofdigital.dobro.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "names")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Integer status;

    @ElementCollection
    @CollectionTable(name = "permissions",
            joinColumns = @JoinColumn(name = "role_id"))
    private List<String> permission;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }
}