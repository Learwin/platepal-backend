package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.ZutatDerWoche;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.time.LocalDate;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "zutat", type = Join.Type.FETCH)
public interface ZutatDerWocheRepository extends PageableRepository<ZutatDerWoche, Long> {

    Optional<ZutatDerWoche> findByVonLessThanEqualsAndBisGreaterThanEquals(LocalDate von, LocalDate bis);


}
