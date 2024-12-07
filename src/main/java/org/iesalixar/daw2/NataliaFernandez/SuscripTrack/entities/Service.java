package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "services") // Corrección del nombre de la tabla
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{msg.service.code.notEmpty}")
    @Size(max = 20, message = "{msg.service.code.size}")
    @Column(name = "code", nullable = false, length = 20)
    private String code;


    @NotEmpty(message = "{msg.service.name.notEmpty}")
    @Size(max = 100, message = "{msg.service.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "{msg.service.description.notEmpty}")
    @Size(max = 250, message = "{msg.service.description.size}")
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @NotNull(message = "{msg.service.category.notNull}")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_category_service"))
    private Category category; // Cambiado de 'categories' a 'category'
}
