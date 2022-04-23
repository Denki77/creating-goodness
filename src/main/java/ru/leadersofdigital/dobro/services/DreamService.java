package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leadersofdigital.dobro.dto.DreamDto;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Dream;
import ru.leadersofdigital.dobro.repositories.DreamRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DreamService {
    private final DreamRepository dreamRepository;

    public Page<Dream> findPage(int page, int pageSize) {
        return dreamRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public Page<Dream> findPage(int page, int pageSize, Long profileId) {
        return dreamRepository.findAllByProfileId(PageRequest.of(page, pageSize), profileId);
    }

    public Optional<Dream> findById(Long id) {
        return dreamRepository.findById(id);
    }

    @Transactional
    public DreamDto createNewDream(DreamDto dreamDto) {
        Dream dream = new Dream();
        dream.setDescription(dreamDto.getDescription());
        dream.setAnnotation(dreamDto.getAnnotation());
        dream.setProfile(dreamDto.getProfile());
        dreamRepository.save(dream);
        return new DreamDto(dream);
    }

    @Transactional
    public DreamDto updateDream(DreamDto dreamDto) {
        Dream dream = findById(dreamDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Dream doesn't exists id: " + dreamDto.getId() + " (for update)"));
        dream.setDescription(dreamDto.getDescription());
        dream.setAnnotation(dreamDto.getAnnotation());
        return new DreamDto(dream);
    }

    public void deleteById(Long id) {
        dreamRepository.deleteById(id);
    }

    public Optional<Dream> findByIdAndProfileId(Long id, Long profileId) {
        return dreamRepository.findByIdAndProfileId(id, profileId);
    }
}
