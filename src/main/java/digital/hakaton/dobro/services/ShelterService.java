package digital.hakaton.dobro.services;

import digital.hakaton.dobro.dto.ShelterDto;
import digital.hakaton.dobro.models.Shelter;
import digital.hakaton.dobro.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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