package org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.repositories;

import org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServicesRepository extends JpaRepository<Services, Long> {

    // Buscar servicios por nombre
    List<Services> findByName(String name);

    // Buscar servicios por categoría (relación ManyToOne con Categories)
      List<Services> findByCategories_Id(Long categoryId);

    // Verificar si existe un servicio por nombre
    boolean existsByName(String name);

    // Verificar si existe un servicio con un nombre específico excluyendo un ID
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Services s WHERE s.name = :name AND s.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);
}
