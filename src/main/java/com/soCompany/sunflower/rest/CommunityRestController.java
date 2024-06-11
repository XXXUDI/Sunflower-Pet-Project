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

    @GetMapping("/{id}")
    public CommunityReadDto getCommunityById(@RequestParam Integer id) {
        return communityService.findCommunityById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // TODO: Make this method works
//    @GetMapping("/{name}")
//    public CommunityReadDto getCommunityByName(@RequestParam String name) {
//        return communityService.findCommunityByName(name)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCommunity(@RequestBody CommunityEditDto communityEditDto) {
        CommunityReadDto communityReadDto = communityService.save(communityEditDto);
        return "redirect:/community/" + communityReadDto.getId();
    }

    @PutMapping("/{id}")
    public CommunityReadDto updateCommunity(@PathVariable Integer id, @RequestBody CommunityEditDto communityEditDto) {
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
