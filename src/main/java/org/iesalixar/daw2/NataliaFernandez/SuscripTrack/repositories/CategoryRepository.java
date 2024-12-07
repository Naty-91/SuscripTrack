package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories;

import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Buscar una categoría por su código
    Optional<Category> findByCode(String code);

    // Buscar una categoría por id (devuelve Optional para evitar NullPointerException)
    Optional<Category> findById(Long id);

    // Verificar si existe una categoría por código
    boolean existsByCode(String code);

    // Verificar si existe una categoría por nombre
    boolean existsByName(String name);

    // Verificar si existe una categoría por nombre pero ignorando su ID actual (para la validación de edición)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Category c WHERE c.name = :name AND c.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);

}
