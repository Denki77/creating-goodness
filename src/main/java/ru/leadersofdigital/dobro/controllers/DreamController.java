package ru.leadersofdigital.dobro.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.DreamDto;
import ru.leadersofdigital.dobro.errorHandling.InvalidDataException;
import ru.leadersofdigital.dobro.errorHandling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Dream;
import ru.leadersofdigital.dobro.models.Token;
import ru.leadersofdigital.dobro.services.DreamService;
import ru.leadersofdigital.dobro.services.ProfileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@Tag(name = "Dream", description = "The Dream API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dream")
public class DreamController {
    private final DreamService dreamService;
    private final ProfileService profileService;

    @Operation(summary = "Gets all dreams", tags = "Dream")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all dreams",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DreamDto.class)))
                    })
    })
    @GetMapping
    public Page<DreamDto> getAllDreams(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "count", defaultValue = "10") int count
    ) {
        Page<Dream> dreamsPage = dreamService.findPage(page - 1, count);
        return new PageImpl<>(dreamsPage.getContent().stream().map(DreamDto::new).collect(Collectors.toList()), dreamsPage.getPageable(), dreamsPage.getTotalElements());
    }

    @Operation(summary = "Get dream by id", tags = "Dream")
    @GetMapping("/{id}")
    public DreamDto getOneDreamById(@PathVariable Long id) {
        Dream dream = dreamService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dream doesn't exists id: " + id));
        return new DreamDto(dream);
    }

    @Operation(summary = "Get dreams per pages", tags = "Dream")
    @GetMapping("/edit")
    public Page<DreamDto> getAllOwnDreams(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "count", defaultValue = "10") int count
    ) {
        Long profileId = this.getProfileIdByAuthenticate();
        Page<Dream> dreamsPage = dreamService.findPage(page - 1, count, profileId);
        return new PageImpl<>(dreamsPage.getContent().stream().map(DreamDto::new).collect(Collectors.toList()), dreamsPage.getPageable(), dreamsPage.getTotalElements());
    }

    @Operation(summary = "Get one dream by id", tags = "Dream")
    @GetMapping("/edit/{id}")
    public DreamDto getOneOwnDreamById(@PathVariable Long id, HttpServletResponse httpResponse) throws IOException {
        // find owner of dream
        Long ownerId = dreamService.findById(id).orElseThrow().getProfile().getId();
        if (!Objects.equals(ownerId, this.getUserIdByAuthenticate())) {
            httpResponse.sendError(403);
            httpResponse.sendRedirect("/");
            return new DreamDto();
        }
        Long profileId = this.getProfileIdByAuthenticate();
        Dream dream = dreamService.findByIdAndProfileId(id, profileId).orElseThrow(() -> new ResourceNotFoundException("Dream doesn't exists id: " + id));
        return new DreamDto(dream);
    }

    @Operation(summary = "Create new dream", tags = "Dream")
    @PostMapping("/edit")
    public DreamDto createNewDream(@RequestBody @Validated DreamDto DreamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return dreamService.createNewDream(DreamDto);
    }

    @Operation(summary = "Update dream", tags = "Dream")
    @PutMapping("/edit")
    public DreamDto updateDream(@RequestBody DreamDto DreamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return dreamService.updateDream(DreamDto);
    }

    @Operation(summary = "Deletez dream by id", tags = "Dream")
    @DeleteMapping("/edit/{id}")
    public void deleteById(@PathVariable Long id) {
        dreamService.deleteById(id);
    }

    private Long getProfileIdByAuthenticate() {
        return profileService.getProfileDtoByUserId(this.getUserIdByAuthenticate()).getUserId();
    }

    private Long getUserIdByAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Token token = (Token) authentication.getDetails();
        return token.getUserId();
    }
}
