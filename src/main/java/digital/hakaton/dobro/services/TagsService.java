package digital.hakaton.dobro.services;

import digital.hakaton.dobro.dto.TagDto;
import digital.hakaton.dobro.repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    public List<TagDto> getAll(){
        return tagsRepository.findAll().stream().map(tag -> {
            TagDto tagDto = new TagDto();
            tagDto.setName(tag.getTags());
            return tagDto;
        }).collect(Collectors.toList());
    }
}
