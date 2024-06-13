package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.CommunityMemberEditDto;
import com.soCompany.sunflower.dto.CommunityMemberReadDto;
import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.entity.fields.CommunityMemberId;
import com.soCompany.sunflower.services.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/communityMember")
@RequiredArgsConstructor
public class CommunityMemberRestController {
    private final CommunityMemberService communityMemberService;

    // Method to get all Users in community
    @GetMapping("/all/{id}")
    public List<UserReadDto> findAllUsersByCommunityId(@RequestParam int id) {
        return communityMemberService.findAllUsersByCommunityId(id);
    }


    // Method to find CommunityMember
    @GetMapping("/member/{id}")
    public CommunityMemberReadDto findCommunityMemberById(@RequestBody CommunityMemberId id) {
        return communityMemberService.findCommunityMemberById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Method to save (subscribe) user to community
    @PostMapping
    public String save(@RequestBody CommunityMemberEditDto communityMemberEditDto)
    {
        var cm = communityMemberService.save(communityMemberEditDto);
        return "redirect:/ " + cm.getCommunityReadDto().getId();
    }

    // Method to update CommunityMember (Example: change his role)
    @PutMapping
    public CommunityMemberReadDto update(@RequestBody CommunityMemberEditDto communityMemberEditDto) {
        return communityMemberService.update(communityMemberEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Method to delete / unsubscribe user from community
    @DeleteMapping
    public void delete(@RequestBody CommunityMemberId communityMemberId) {
        if(!communityMemberService.delete(communityMemberId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
