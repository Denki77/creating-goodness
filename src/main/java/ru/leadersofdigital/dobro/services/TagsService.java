package ru.leadersofdigital.dobro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.TagDto;
import ru.leadersofdigital.dobro.repositories.TagsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    public List<TagDto> getAll(){
        return tagsRepository.findAll().stream().map(tag -> {
            TagDto tagDto = new TagDto();
            tagDto.setName(tag.getName());
            tagDto.setName(tagDto.getName());
            return tagDto;
        }).collect(Collectors.toList());
    }
}
