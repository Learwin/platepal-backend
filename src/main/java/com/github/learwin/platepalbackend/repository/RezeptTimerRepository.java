package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.RezeptTimer;
import com.github.learwin.platepalbackend.entity.ids.RezeptTimerId;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "timer_id", type = Join.Type.FETCH)
@Join(value = "rezept_id", type = Join.Type.FETCH)
public interface RezeptTimerRepository extends PageableRepository<RezeptTimer, RezeptTimerId> {
    List<RezeptTimer> findByRezept_id(Rezept rezept_id);
}
