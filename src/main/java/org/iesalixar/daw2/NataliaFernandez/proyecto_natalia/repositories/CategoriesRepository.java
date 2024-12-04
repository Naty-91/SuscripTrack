package org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.repositories;
import org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    // Buscar una categoría por su código
    Optional<Categories> findByCode(String code);

    // Buscar una categoría por su nombre
    Optional<Categories> findByName(String name);

    // Verificar si existe una categoría por código
    boolean existsByCode(String code);

    // Verificar si existe una categoría por nombre
    boolean existsByName(String name);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Categories c WHERE c.name = :name AND c.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);


}
