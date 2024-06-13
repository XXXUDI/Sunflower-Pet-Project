package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.CommunityEditDto;
import com.soCompany.sunflower.dto.CommunityReadDto;
import com.soCompany.sunflower.services.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityRestController {
    private final CommunityService communityService;

    @GetMapping
    public CommunityReadDto getCommunity(@RequestParam(required = false) Integer id,
                                         @RequestParam(required = false) String name) {
        if (id != null) {
            return communityService.findCommunityById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else if (name != null) {
            return communityService.findCommunityByName(name)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Either id or name must be provided");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCommunity(@RequestBody CommunityEditDto communityEditDto) {
        CommunityReadDto communityReadDto = communityService.save(communityEditDto);
        return "redirect:/community/" + communityReadDto.getId();
    }

    @PutMapping("/{id}")
    public CommunityReadDto updateCommunity(@RequestParam Integer id, @RequestBody CommunityEditDto communityEditDto) {
        return communityService.update(id, communityEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteCommunity(@PathVariable Integer id) {
        if(!communityService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
