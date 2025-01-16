package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.Rezept;
import com.github.learwin.platepalbackend.entity.ZutatRezept;
import com.github.learwin.platepalbackend.entity.ids.ZutatRezeptId;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "zutat_id", type = Join.Type.FETCH)
@Join(value = "rezept_id", type = Join.Type.FETCH)
@Join(value = "einheit_id", type = Join.Type.FETCH)
public interface ZutatRezeptRepository extends PageableRepository<ZutatRezept, ZutatRezeptId> {

    List<ZutatRezept> findByRezept_id(Rezept rezept_id);
}
