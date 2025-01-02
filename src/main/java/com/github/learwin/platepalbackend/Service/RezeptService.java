package com.github.learwin.platepalbackend.Service;

import com.github.learwin.platepalbackend.DTO.RezeptDto;
import com.github.learwin.platepalbackend.repository.RezeptRepository;
import jakarta.inject.Singleton;

@Singleton
public class RezeptService {
    private final RezeptRepository rezeptRepository;

    public RezeptService(RezeptRepository rezeptRepository) {
        this.rezeptRepository = rezeptRepository;
    }

    public RezeptDto getRezeptWithZutaten(Long id) {
        return rezeptRepository.findByIdWithZutaten(id)
                .map(rezept -> {
                    RezeptDto dto = new RezeptDto();
                    dto.setName(rezept.getName());
                    dto.setZutaten(rezept.getZutaten().stream().map(rz -> {
                        RezeptDto.ZutatRezeptDto zutatDTO = new RezeptDto.ZutatRezeptDto();
                        zutatDTO.setZutat(rz.getZutat_id());
                        zutatDTO.setMenge(rz.getMenge());
                        zutatDTO.setEinheit(rz.getEinheitId());
                        return zutatDTO;
                    }).toList());
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("Rezept nicht gefunden"));
    }
}
