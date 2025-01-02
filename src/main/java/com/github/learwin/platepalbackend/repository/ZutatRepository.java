package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Zutat;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "allergene", type = Join.Type.FETCH)
public interface ZutatRepository extends PageableRepository<Zutat, Long> {
    @Query("SELECT z FROM Zutat z JOIN FETCH z.rezeptZutaten rz JOIN FETCH rz.rezept WHERE z.id = :id")
    Optional<Zutat> findByIdWithRezepte(Long id);
}
