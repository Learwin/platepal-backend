package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Allergen;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "zutaten", type = Join.Type.LEFT_FETCH)
public interface AllergenRepository extends PageableRepository<Allergen, Long> {
}
