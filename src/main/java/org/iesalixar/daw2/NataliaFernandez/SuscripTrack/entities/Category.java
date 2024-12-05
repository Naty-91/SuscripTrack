package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "services") // Evita recursividad infinita
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{msg.category.code.notEmpty}")
    @Size(max = 2, message = "{msg.category.code.size}")
    @Column(name = "code", nullable = false, length = 5)
    private String code;

    @NotEmpty(message = "{msg.category.name.notEmpty}")
    @Size(max = 100, message = "{msg.category.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Service> services = new ArrayList<>();
}
