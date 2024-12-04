package org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // Marca esta clase como una entidad JPA.
@Table(name = "sevices") // Define el nombre de la tabla asociada a esta entidad.
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{msg.services.name.notEmpty}")
    @Size(max = 100, message = "{msg.services.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "{msg.services.description.notEmpty}")
    @Size(max = 250, message = "{msg.services.description.size}")
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @NotNull(message = "{msg.services.category.notNull}")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_category_services"))
    private Categories categories;

}
