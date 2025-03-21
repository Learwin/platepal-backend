package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Einheit;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface EinheitRepository extends PageableRepository<Einheit, Long> {
}
