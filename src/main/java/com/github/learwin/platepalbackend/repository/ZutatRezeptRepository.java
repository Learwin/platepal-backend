package com.github.learwin.platepalbackend.repository;

import com.github.learwin.platepalbackend.DTO.ZutatRezeptDto;
import com.github.learwin.platepalbackend.entity.Zutat;
import com.github.learwin.platepalbackend.entity.ZutatRezept;
import com.github.learwin.platepalbackend.entity.ids.ZutatRezeptId;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@Join(value = "zutat_id", type = Join.Type.FETCH)
@Join(value = "rezept_id", type = Join.Type.FETCH)
@Join(value = "einheit_id", type = Join.Type.FETCH)
public interface ZutatRezeptRepository extends PageableRepository<ZutatRezept, ZutatRezeptId> {
    @Query("SELECT * FROM Zutat_Rezept WHERE REZEPT_ID = :rezept_id")
    ZutatRezeptDto findZutatenByRezeptId(Long rezept_id);
}
