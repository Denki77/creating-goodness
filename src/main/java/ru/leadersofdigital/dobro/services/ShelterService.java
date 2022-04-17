package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.ShelterDto;
import ru.leadersofdigital.dobro.models.Shelter;
import ru.leadersofdigital.dobro.repositories.ShelterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final ShelterRepository repository;

    public List<ShelterDto> getMeAllSheltersAsShelterDtoS() {
        List<ShelterDto> shelterDtoS = new ArrayList<>();
        for (Shelter shelter : repository.findAll()) {
            ShelterDto shelterDto = fillShelterDtoByShelter(shelter);
            shelterDtoS.add(shelterDto);
        }
        return shelterDtoS;
    }

    public List<ShelterDto> getMeAllSheltersAsShelterDtoS(String q) {
        List<ShelterDto> shelterDtoS = new ArrayList<>();
        for (Shelter shelter : repository.findByNameStartsWith(q)) {
            ShelterDto shelterDto = fillShelterDtoByShelter(shelter);
            shelterDtoS.add(shelterDto);
        }
        return shelterDtoS;
    }

    private ShelterDto fillShelterDtoByShelter(Shelter shelter) {
        ShelterDto shelterDto = new ShelterDto();
        shelterDto.setId(shelter.getId());
        shelterDto.setNumber(shelter.getNumber());
        shelterDto.setCity_id(shelter.getCity().getId());
        shelterDto.setName(shelter.getName());
        shelterDto.setAnnotation(shelter.getAnnotation());
        shelterDto.setDescription(shelter.getDescription());
        shelterDto.setUpdated_at(shelter.getUpdatedAt().toString());
        shelterDto.setCreated_at(shelter.getCreatedAt().toString());
        return shelterDto;
    }
}