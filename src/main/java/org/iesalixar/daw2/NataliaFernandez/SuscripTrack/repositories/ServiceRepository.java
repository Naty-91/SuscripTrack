package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories;

import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    // Buscar servicios por nombre
    List<Service> findByName(String name);

    // Buscar servicios por categoría (relación ManyToOne con Categories)
    List<Service> findByCategoryId(Long categoryId); // Usar "category" en singular

    // Devuelve objeto Optional en caso de que encuentre un objeto Service o no según el id proporcionado
    Optional<Service> findById(Long id);

    // Verificar si existe un servicio por nombre
    boolean existsByName(String name);

    // Verificar si existe un servicio con un nombre específico excluyendo un ID
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Service s WHERE s.name = :name AND s.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);

    boolean existsServiceByCode(String code);
}
