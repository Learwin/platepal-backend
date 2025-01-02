package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Rezept;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "user_Id", type = Join.Type.FETCH)
public interface RezeptRepository extends PageableRepository<Rezept, Long> {
    @Query("SELECT r.* FROM rezept r JOIN zutat_rezept rz ON r.id = rz.rezept_id JOIN zutat z ON rz.zutat_id = z.id WHERE r.id = :id")
    Optional<Rezept> findByIdWithZutaten(Long id);
    Page<Rezept> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
