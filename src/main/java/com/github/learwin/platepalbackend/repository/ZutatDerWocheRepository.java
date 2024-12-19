package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.ZutatDerWoche;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
@Join(value = "zutat", type = Join.Type.FETCH)
public interface ZutatDerWocheRepository extends PageableRepository<ZutatDerWoche, Long> {


    @Query("""
            select * from ZutatDerWoche zdw 
            where zdw.von <= current_date AND zdw.bis >= current_date
           """)
    Optional<ZutatDerWoche> getCurrentZDW();


}
