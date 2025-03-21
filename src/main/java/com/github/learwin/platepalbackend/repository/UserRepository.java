package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.entity.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface UserRepository extends PageableRepository<User, Long> {
    Optional<User> getByemailAdresse(String email);
}
