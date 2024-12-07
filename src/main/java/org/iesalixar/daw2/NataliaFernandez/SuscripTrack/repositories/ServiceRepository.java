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

    // Buscar servicios por categoría
    List<Service> findByCategoryId(Long categoryId);


    // Buscar un servicio por ID
    Optional<Service> findById(Long id);

    // Verificar si existe un servicio por código
    boolean existsServiceByCode(String code);

    // Verificar si existe un servicio con un código específico excluyendo un ID
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Service s WHERE s.code = :code AND s.id <> :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
}
