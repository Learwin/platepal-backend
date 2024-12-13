package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Bewertung;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "rezept_id", type = Join.Type.FETCH)
@Join(value = "user_Id", type = Join.Type.FETCH)
public interface BewertungRepository extends PageableRepository<Bewertung, Long> {
}
