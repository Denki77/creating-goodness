package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.DreamDto;
import ru.leadersofdigital.dobro.error_handling.InvalidDataException;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Dream;
import ru.leadersofdigital.dobro.services.DreamService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dream")
public class DreamController {
    private final DreamService dreamService;

    @GetMapping
    public Page<DreamDto> getAllDreams(@RequestParam(name = "p", defaultValue = "1") int page) {
        Page<Dream> dreamsPage = dreamService.findPage(page - 1, 10);
        Page<DreamDto> dtoPage = new PageImpl<>(dreamsPage.getContent().stream().map(DreamDto::new).collect(Collectors.toList()), dreamsPage.getPageable(), dreamsPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping("/{id}")
    public DreamDto getOneDreamById(@PathVariable Long id) {
        Dream dream = dreamService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dream doesn't exists id: " + id));
        return new DreamDto(dream);
    }

    @PostMapping
    public DreamDto createNewDream(@RequestBody @Validated DreamDto DreamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return dreamService.createNewDream(DreamDto);
    }

    @PutMapping
    public DreamDto updateDream(@RequestBody DreamDto DreamDto) {
        return dreamService.updateDream(DreamDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        dreamService.deleteById(id);
    }
}
