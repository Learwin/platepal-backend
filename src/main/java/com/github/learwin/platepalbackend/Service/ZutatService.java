package com.github.learwin.platepalbackend.Service;

import com.github.learwin.platepalbackend.DTO.ZutatDto;
import com.github.learwin.platepalbackend.repository.ZutatRepository;
import jakarta.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class ZutatService {
    private final ZutatRepository zutatRepository;

    public ZutatService(ZutatRepository zutatRepository) {
        this.zutatRepository = zutatRepository;
    }

    public ZutatDto getZutatWithRezepte(Long id) {
        return zutatRepository.findByIdWithRezepte(id)
                .map(zutat -> {
                    ZutatDto dto = new ZutatDto();
                    dto.setName(zutat.getName());
                    dto.setRezepte(zutat.getRezeptZutaten().stream().map(rz -> {
                        ZutatDto.RezeptDTO rezeptDTO = new ZutatDto.RezeptDTO();
                        rezeptDTO.setName(rz.getRezept_id().getName());
                        rezeptDTO.setMenge(rz.getMenge());
                        rezeptDTO.setEinheit(rz.getEinheitId());
                        return rezeptDTO;
                    }).collect(Collectors.toList()));
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("Zutat nicht gefunden"));
    }
}
