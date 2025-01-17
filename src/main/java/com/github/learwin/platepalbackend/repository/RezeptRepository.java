package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Rezept;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "user_Id", type = Join.Type.LEFT_FETCH)
public interface RezeptRepository extends PageableRepository<Rezept, Long> {
    Page<Rezept> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
